package be.occam.velo.domain.people;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.velo.domain.object.Map;
import be.occam.velo.repository.MapEntity;
import be.occam.velo.repository.MapRepository;

import com.google.appengine.api.datastore.KeyFactory;

public class Mapper {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	protected MapRepository mapRepository;
	
    public Mapper() {
    }
    
    public Map create( Map map ) {
    	
    	return this.create( map, false );
    	
    }
    
    public Map create( Map map, boolean sendEmail ) {
    	
    	MapEntity entity
    		= Map.entity( map );
    	
    	MapEntity saved 
    		= this.mapRepository.saveAndFlush( entity );
    	
    	saved.setUuid( KeyFactory.keyToString( saved.getKey() ) );
    	
    	saved 
			= this.mapRepository.saveAndFlush( saved );
    	
    	logger.info( "created map for [{}] with uuid [{}] and radius [{}]", new Object[] { saved.getUserID(), saved.getUuid(), saved.getRadius() } );
    	
    	return Map.from(saved);
    	
    }

    public Map update( String uuid, Map map ) {
    	
    	Map loaded 
    		= map( uuid );
    	
    	if ( loaded == null ) {
    		return null;
    	}
    	
    	MapEntity entity
			= Map.entity( map );
	
    	MapEntity saved 
			= this.mapRepository.saveAndFlush( entity );
    	
    	logger.info( "updated map for [{}] with uuid [{}] and radius [{}]", new Object[] { saved.getUserID(), saved.getUuid(), saved.getRadius() } );
    	
    	return Map.from(saved);
    	
    }
    
    protected Map map( String id ) {
    	
    	MapEntity entity
    		= this.mapRepository.findOneByUuid( id );
    	
    	if ( entity != null ) {
    		logger.debug( "found Map with id [{}]", id );
    	}
    	
    	return Map.from( entity );
    	
    }
    
    public Map findOneByUuid( String uuid ) {
    	return this.map( uuid );
    }
    
    public Map findOneByUserID( String userID ) {
    	
    	Map map
    		= null;
    	
    	MapEntity loaded
    		= this.mapRepository.findOneByUserID( userID );
    	
    	if ( loaded != null ) {
    		map = Map.from( loaded );
    	}
    	
    	return map;
    	
    }
    
    
    
    public List<Map> all( ) {
    	
    	List<MapEntity> entities
    		= this.mapRepository.findAll();
    	
    	List<Map> filtered
			= new ArrayList<Map>();
    	
    	for ( MapEntity entity : entities ) {
    		
    		filtered.add( Map.from( entity ) );
    		
    	}
    	
    	return filtered;
    	
    	
    }
   
}