package be.occam.velo.domain.people;

import static be.occam.utils.javax.Utils.list;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strava.ApiClient;
import com.strava.ApiException;
import com.strava.SummaryActivity;
import com.strava.api.ActivitiesApi;

import be.occam.strava.object.Ride;
import be.occam.strava.object.Rider;
import be.occam.strava.object.Token;
import be.occam.strava.people.Stravanticator;
import be.occam.velo.domain.exception.AuthorizationExpiredException;
import be.occam.velo.domain.util.Timing;

public class RideRover {
	
	protected final Logger logger = LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	Stravanticator stravanticator;
	
	/**
	 * Finds all rides of rider for the provided month, if any. Returns the rides for the current month if parameter month is not provided.
	 */
	public List<Ride> findRidesOfRiderForMonth( Rider rider, Integer month ) {
		return this.findRidesOfRiderForMonth( rider, month,false );
	}
	
	public List<Ride> findRidesOfRiderForMonth( Rider rider, Integer month, boolean throwIt ) {
		
		List<Ride> rides
			= list();
		
		if ( rider != null ) {
			
			Date since
				= Timing.currentMonthStart();
			
			Date to 
				= null;
			
			if ( month != null ) {
				since = Timing.monthStart( month );
				to = Timing.monthEnd( month );
			}
			
			List<Ride> ridesSince
				= this.ridesForRider( rider, since, to, throwIt );
			
			if ( ridesSince != null ) {
				rides.addAll( ridesSince );
			}
			
		}
		
		return rides;
		
	}
	
	public List<Ride> ridesForRiderSince( Rider rider, Date since, boolean throwIt ) {
		return this.ridesForRider( rider, since, null, throwIt );
	}
	
	public List<Ride> ridesForRider( Rider rider, Date since, Date to, boolean throwIt ) {
		
		logger.info("[{}]; get rides from [{}] till [{}]", rider.getRiderID(), Timing.moment(since), to != null ?  Timing.moment( to ) : "now" ); 
		
		List<Ride> rides 
			= list();
		
		List<SummaryActivity> activities 
			= this.activitiesForRiderSince(rider, since,to, throwIt);
		
		for ( SummaryActivity activity : activities ) {
			
			Ride ride
				= this.activityToRide( activity, rider );
			
			rides.add( ride );
		}
		
		return rides;
		
	}
	
	public List<SummaryActivity> activitiesForRiderSince( Rider rider, Date since ) {
		return activitiesForRiderSince(rider,since,null,false);
	}
	
	public List<SummaryActivity> activitiesForRiderSince( Rider rider, Date since, Date to ) {
		return this.activitiesForRiderSince(rider, since, to, false );
	}
		
	public List<SummaryActivity> activitiesForRiderSince( Rider rider, Date since, Date to, boolean throwIt ) {
		
		List<SummaryActivity> activities 
			= list();
			
		try {
			
			String staffNumber
				= rider.getRiderID();
			
			Token token
				= this.stravanticator.findToken( staffNumber );
			
			String accessToken
				= token.getAccessToken();
			
			logger.info( "[{}], current access token is [{}]", staffNumber, accessToken );
			
			ApiClient client
				= new ApiClient( );
		
			client.setAccessToken( accessToken );
			
			logger.debug( "[{}], get activities, using access token [{}]", staffNumber, accessToken );
	
			ActivitiesApi activitiesAPI
				= new ActivitiesApi( client );
			
			Date afterDate
			 	= since;
			
			logger.debug( "[{}]; fetching acivities since [{}]", staffNumber, Timing.moment( afterDate ) );
			
			Long afterEpoch
				= ( afterDate.getTime() / 1000 );
			
			Long beforeEpoch
				= ( to != null ? ( to.getTime() / 1000 ) : null );
			
			List<SummaryActivity> allActivities = activitiesAPI.getLoggedInAthleteActivities( beforeEpoch != null ? beforeEpoch.intValue() : null, afterEpoch.intValue(), 1, 100 );
	
			for ( SummaryActivity a : allActivities ) {
				
				if ( a.isCommute() ) {
				// logger.info( "member athlete {} is {} {}", a.getId(), a.getFirstname(), a.getLastname() );
					logger.debug( "[{}]; commute activity {}", staffNumber, a.toString() );
					activities.add( a );
				}
				else {
					logger.debug( "[{}]; not a commute activity {}", staffNumber, a.getName() );
				}
			}
			
		} catch ( ApiException e ) {
			logger.warn("strava api exception, code is [{}], message is [{}], body is [{}]", e.getCode(), e.getMessage(), e.getResponseBody() );
			if ( 401 == e.getCode() ) {
				logger.warn( "unauthorized");
				if ( throwIt ) {
					throw new AuthorizationExpiredException( rider.getRiderID() );
				}
			}
		}
		catch ( Exception e ) {
			throw new RuntimeException( e );
		}
			
		return activities;
		
		
	}
	
	public Ride activityToRide( SummaryActivity activity, Rider rider ) {
		
		Ride ride
			= new Ride();
		
		ride.setRiderID( rider.getRiderID() );
		// TODO ID = ok ?
		ride.setId( activity.getId() );
		ride.setName( activity.getName() );
		ride.setMoment( activity.getStartDate().toDate() );
		ride.setTeamID( rider.getTeamID() );
		ride.setStravaID( String.format("%d",activity.getId() ) );
		
		// activity distance is in meters
		Float kilometers = activity.getDistance() / 1000F;
		BigDecimal bg = BigDecimal.valueOf( kilometers );
		bg.setScale( 2, RoundingMode.HALF_UP );
		ride.setDistance( bg.floatValue() );
		
		return ride;
		
	}

	public Stravanticator getStravanticator() {
		return stravanticator;
	}

	public void setStravanticator(Stravanticator stravanticator) {
		this.stravanticator = stravanticator;
	}
	
}
