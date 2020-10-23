package be.occam.velo.cmd;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.strava.object.Ride;
import be.occam.strava.object.Rider;
import be.occam.strava.object.Token;
import be.occam.strava.people.Stravanticator;
import be.occam.strava.repository.TokenEntity;
import be.occam.strava.repository.TokenRepository;
import be.occam.strava.repository.impl.HashMapTokenRepository;
import be.occam.utils.timing.Timing;
import be.occam.velo.domain.people.RideRover;

public class GatherFavourites {
	
	protected final Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	public GatherFavourites() {
		
	}
	
	public void gather() {
		
		String riderID = "sven";
		String riderStravaID = "420336";
		String authorizationCode = "2e00fadb7b0e76ec943a9ecca7cb515b22da8599";
		String accessToken = "6a0611b2820400f19bf2a2ab9a14c1ee708cc237";
		
		String clientID = System.getProperty( "strava.client.id" );
		String clientSecret = System.getProperty( "strava.client.secret" );
		String redirectURI = "https://2wwd.t.icts.kuleuven.be";
		
		Stravanticator stravanticator 
			= new Stravanticator(clientID, clientSecret, redirectURI);
		
		TokenRepository tokenRepository 
			= new HashMapTokenRepository();
		
		stravanticator.setTokenRepository( tokenRepository );
		
		TokenEntity entity 
			= new TokenEntity();
		entity.setRiderID(riderID);
		//entity.setCode( authorizationCode );
		entity.setAccessToken( accessToken );
		entity.setAccessTokenExpiration( Timing.moment("14/10/2020", "01:00"));
		tokenRepository.save(entity);
		
		Token token 
			= stravanticator.token(riderID);
		
		logger.info( "access token is [{}]", token.getAccessToken() );
		logger.info( "refresh token is [{}]", token.getRefreshToken() );
		
		RideRover rideRover 
			= new RideRover();
		rideRover.setStravanticator( stravanticator );
		
		Rider rider 
			= new Rider();
		rider.setRiderID( riderID );
		rider.setStravaID( riderStravaID );
		
		Date since = Timing.date( "01/10/2020" );
		List<Ride> rides = rideRover.ridesForRiderSince(rider, since, true);
		
		logger.info( "found [{}] rides", rides.size() );
		
	}
	
	
	public static void main(String[] args) {
		try {
			new GatherFavourites().gather();
		}
		catch( Throwable e ) {
			e.printStackTrace(System.err);
		}
		
	}

}
