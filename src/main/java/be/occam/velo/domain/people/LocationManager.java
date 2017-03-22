package be.occam.velo.domain.people;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.velo.domain.object.Location;
import be.occam.velo.repository.LocationEntity;
import be.occam.velo.repository.LocationRepository;

import com.google.appengine.api.datastore.KeyFactory;

public class LocationManager {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Comparator<Location> lastUpdatedFirst
		= new Comparator<Location>() {

			@Override
			public int compare(Location o1, Location o2) {
				return 0 - ( o1.getMoment().compareTo( o2.getMoment() ) );
			}
		
		};
	
	@Resource
	protected LocationRepository locationRepository;
	
    public LocationManager() {
    }
    
    public Location create( Location location ) {
    	
    	return this.create( location, false );
    	
    }
    
    public Location create( Location location, boolean sendEmail ) {
    	
    	LocationEntity entity
    		= Location.entity( location );
    	
    	LocationEntity saved 
    		= this.locationRepository.saveAndFlush( entity );
    	
    	saved.setUuid( KeyFactory.keyToString( saved.getKey() ) );
    	
    	saved 
			= this.locationRepository.saveAndFlush( saved );
    	
    	logger.info( "created location for ride [{}], rider [{}]: latitude [{}] and longitude [{}]", new Object[] { saved.getRideID(), saved.getRiderID(), saved.getLongitude(), saved.getLattitude() } );
    	
    	return Location.from(saved);
    	
    }

    public Location update( String uuid, Location location ) {
    	
    	Location loaded 
    		= location( uuid );
    	
    	if ( loaded == null ) {
    		return null;
    	}
    	
    	LocationEntity entity
			= Location.entity( location );
	
    	LocationEntity saved 
			= this.locationRepository.saveAndFlush( entity );
    	
    	logger.info( "updated Location with uiid [{}]", saved.getUuid() );
    	
    	return Location.from(saved);
    	
    }
    
    protected Location location( String id ) {
    	
    	LocationEntity entity
    		= this.locationRepository.findByUuid( id );
    	
    	if ( entity != null ) {
    		logger.debug( "found Location with id [{}]", id );
    	}
    	
    	return Location.from( entity );
    	
    }
    
    public Location findOneByUuid( String uuid ) {
    	return this.location( uuid );
    }
    
    public List<Location> all( ) {
    	
    	List<LocationEntity> entities
    		= this.locationRepository.findAll();
    	
    	List<Location> filtered
			= new ArrayList<Location>();
    	
    	for ( LocationEntity entity : entities ) {
    		
    		filtered.add( Location.from( entity ) );
    		
    	}
    	
    	Collections.sort( filtered , this.lastUpdatedFirst );
    	
    	return filtered;
    	
    	
    }
    
  public List<Location> findByRideID( String rideID ) {
    	
    	List<LocationEntity> entities
    		= this.locationRepository.findByRideID( rideID );
    	
    	List<Location> filtered
			= new ArrayList<Location>();
    	
    	for ( LocationEntity entity : entities ) {
    		
    		filtered.add( Location.from( entity ) );
    		
    	}
    	
    	Collections.sort( filtered , this.lastUpdatedFirst );
    	
    	return filtered;
    	
    	
    }
   
}