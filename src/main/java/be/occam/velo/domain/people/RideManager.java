package be.occam.velo.domain.people;

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
    	
    	saved.setUuid( KeyFactory.keyToString( saved.getKey() ) );
    	
    	saved 
			= this.rideRepository.saveAndFlush( saved );
    	
    	logger.info( "created ride with uuid [{}] and title [{}]", new Object[] { saved.getUuid(), saved.getTitle() } );
    	
    	return Ride.from(saved);
    	
    }

    public Ride update( String uuid, Ride ride ) {
    	
    	Ride loaded 
    		= ride( uuid );
    	
    	if ( loaded == null ) {
    		return null;
    	}
    	
    	RideEntity entity
			= Ride.entity( ride );
	
    	RideEntity saved 
			= this.rideRepository.saveAndFlush( entity );
    	
    	logger.info( "updated Ride with uiid [{}]", saved.getUuid() );
    	
    	return Ride.from(saved);
    	
    }
    
    protected Ride ride( String id ) {
    	
    	RideEntity entity
    		= this.rideRepository.findOneByUuid( id );
    	
    	if ( entity != null ) {
    		logger.debug( "found Ride with id [{}]", id );
    	}
    	
    	return Ride.from( entity );
    	
    }
    
    public Ride findOneByUuid( String uuid ) {
    	return this.ride( uuid );
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