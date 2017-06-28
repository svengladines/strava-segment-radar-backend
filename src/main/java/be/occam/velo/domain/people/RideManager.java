package be.occam.velo.domain.people;

import static be.occam.utils.javax.Utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.velo.domain.object.Ride;
import be.occam.velo.repository.RideEntity;
import be.occam.velo.repository.RideRepository;

import com.google.appengine.api.datastore.KeyFactory;

public class RideManager {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Comparator<Ride> lastUpdatedFirst
		= new Comparator<Ride>() {

			@Override
			public int compare(Ride o1, Ride o2) {
				return 0 - ( o1.getMoment().compareTo( o2.getMoment() ) );
			}
		
		};
	
	@Resource
	protected RideRepository rideRepository;
	
    public RideManager() {
    }
    
    public Ride create( Ride ride ) {
    	
    	return this.create( ride, false );
    	
    }
    
    public Ride create( Ride ride, boolean sendEmail ) {
    	
    	RideEntity entity
    		= Ride.entity( ride );
    	
    	RideEntity saved 
    		= this.rideRepository.saveAndFlush( entity );
    	
    	if ( ride.getUuid() == null ) {
    	
    		saved.setUuid( KeyFactory.keyToString( saved.getKey() ) );
    	}
    	else {
    		saved.setUuid( ride.getUuid() );
    	}
    	
    	saved 
			= this.rideRepository.saveAndFlush( saved );
    	
    	logger.info( "created ride with uuid [{}], title [{}], start [{},{}]", new Object[] { saved.getUuid(), saved.getTitle(), saved.getStartLatitude(), saved.getStartLongitude() } );
    	
    	return Ride.from(saved);
    	
    }

    public Ride update( String uuid, Ride ride ) {
    	
    	RideEntity entity
    		= this.rideRepository.findOneByUuid( uuid );
    	
    	if ( entity == null ) {
    		return null;
    	}
    	
    	if ( ! isEmpty( ride.getTitle() ) ) {
    		entity.setTitle( ride.getTitle() );
    	}
    	
    	if ( ! isEmpty( ride.getDescription() ) ) {
    		entity.setDescription( ride.getDescription() );
    	}
    	
    	if ( ride.getStatus() != null ) {
    		entity.setStatus( ride.getStatus().name() );
    	}
    	
    	if ( ride.getStart() != null ) {
    		entity.setStartLatitude( ride.getStart().getLatitude() );
    		entity.setStartLongitude( ride.getStart().getLongitude() );
    		logger.info( "ride starts at [{},{}]", entity.getStartLatitude(), entity.getStartLongitude() );
    	}
    	
    	RideEntity saved 
			= this.rideRepository.saveAndFlush( entity );
    	
    	logger.info( "updated Ride with uuid [{}]", saved.getUuid() );
    	
    	return Ride.from(saved);
    	
    }
    
    public Ride delete( String uuid ) {
    	
    	RideEntity entity
    		= this.rideRepository.findOneByUuid( uuid );
    	
    	if ( entity == null ) {
    		return null;
    	}
    	
    	this.rideRepository.delete( entity );
    	
    	logger.info( "deleted ride with uuid [{}]", entity.getUuid() );
    	
    	return Ride.from( entity );
    	
    }
    
    protected Ride ride( String id ) {

    	Ride ride 
    		= null;
    	
    	RideEntity entity
    		= this.rideRepository.findOneByUuid( id );
    	
    	if ( entity != null ) {
    		logger.debug( "found Ride with id [{}]", id );
    		ride = Ride.from( entity );
    	}
    	
    	return ride;
    	
    }
    
    public Ride findOneByUuid( String uuid ) {
    	return this.ride( uuid );
    }
    
    public Ride findOneByTitle( String title ) {
    	
    	Ride ride 
			= null;
	
    	RideEntity entity
			= this.rideRepository.findOneByTitle( title );
	
		if ( entity != null ) {
			logger.debug( "found Ride with title [{}]", title );
			ride = Ride.from( entity );
		}
	
		return ride;
    }
    
    
    public List<Ride> all( ) {
    	
    	List<RideEntity> entities
    		= this.rideRepository.findAll();
    	
    	List<Ride> filtered
			= new ArrayList<Ride>();
    	
    	for ( RideEntity entity : entities ) {
    		
    		filtered.add( Ride.from( entity ) );
    		
    	}
    	
    	Collections.sort( filtered , this.lastUpdatedFirst );
    	
    	return filtered;
    	
    	
    }
   
}