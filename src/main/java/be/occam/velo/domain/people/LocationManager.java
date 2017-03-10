package be.occam.velo.domain.people;

import static be.occam.utils.javax.Utils.isEmpty;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;

import be.occam.velo.domain.exception.ErrorCodes;
import be.occam.velo.domain.exception.VeloException;
import be.occam.velo.domain.object.Location;
import be.occam.velo.repository.LocationRepository;

import com.google.appengine.api.datastore.KeyFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

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
    
    public Location create( Location Location ) {
    	return this.create( Location, true );
    }
    
    public Location create( Location location, boolean sendEmail ) {
    	
    	Location saved 
    		= this.locationRepository.saveAndFlush( location );
    	
    	saved.setUuid( KeyFactory.keyToString( saved.getKey() ) );
    	
    	saved 
			= this.locationRepository.saveAndFlush( saved );
    	
    	logger.info( "created location for [{}] with uuid [{}], lattitude [{}] and longitude [{}]", new Object[] { saved.getUserID(), saved.getUuid(), saved.getLongitude(), saved.getLattitude() } );
    	
    	return saved;
    	
    }

    public Location update( String uuid, Location Location ) {
    	
    	Location loaded 
    		= location( uuid );
    	
    	if ( loaded == null ) {
    		return null;
    	}
    	
    	Location saved
    		= this.locationRepository.saveAndFlush( loaded );
    	
    	logger.info( "updated Location with uiid [{}]", saved.getUuid() );
    	
    	return saved;
    	
    }
    
  public Location delete( String uuid ) {
    	
    	Location loaded 
    		= location( uuid );
    	
    	if ( loaded == null ) {
    		return null;
    	}
    	
    	this.locationRepository.delete( loaded );
    	
    	logger.info( "deleted Location with uiid [{}]", uuid );
    	
    	return loaded;
    	
    }
    
    protected Location location( String id ) {
    	
    	Location organisatie
    		= this.locationRepository.findByUuid( id );
    	
    	if ( organisatie != null ) {
    		logger.debug( "found Location with id [{}]", id );
    	}
    	
    	return organisatie;
    	
    }
    
    public Location findOneByUuid( String uuid ) {
    	return this.location( uuid );
    }
    
    public List<Location> all( ) {
    	
    	List<Location> all
    		= this.locationRepository.findAll();
    	
    	List<Location> filtered
			= new ArrayList<Location>();
    	
    	/*
    	
    	for ( Location Location : all ) {
    		
    		if ( PirlewietUtil.isPirlewiet( Location ) ) {
    			continue;
    		}
    		
    		if ( PirlewietUtil.isPD( Location ) ) {
    			continue;
    		}
    		
    		filtered.add( Location );
    		
    	}
    	
    	*/
    	
    	
    	Collections.sort( filtered , this.lastUpdatedFirst );
    	
    	return filtered;
    	
    	
    }
   
}