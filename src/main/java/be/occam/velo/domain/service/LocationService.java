package be.occam.velo.domain.service;

import static be.occam.utils.javax.Utils.list;
import static be.occam.utils.javax.Utils.map;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import be.occam.utils.spring.web.Result;
import be.occam.utils.spring.web.Result.Value;
import be.occam.velo.LocationDTO;
import be.occam.velo.application.util.DataGuard;
import be.occam.velo.domain.object.Location;
import be.occam.velo.domain.people.LocationManager;
import be.occam.velo.web.util.VeloUtil;

public class LocationService {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	DataGuard dataGuard;
	
	@Resource
	LocationManager locationManager;
	
	public LocationService( ) {
		logger.info( "location service started" );
	}
	
	@Transactional( readOnly=true )
	public Result<List<Result<LocationDTO>>> query( String rideID ) {
		
		logger.info( "query, rideID is [{}]", rideID );
		
		Result<List<Result<LocationDTO>>> result
			= new Result<List<Result<LocationDTO>>>();
		
		List<Result<LocationDTO>> individualResults
			= list();
		
		List<Location> locations
			= list( );
		
		Map<String, List<LocationDTO>> map
			= map();
		
		if ( rideID == null ) {
			
			List<Location> all 
				= this.locationManager.all();
			
			locations.addAll( all );
			
		}
		else {
			
			List<Location> loaded 
				= this.locationManager.findByRideID( rideID );
		
			locations.addAll( loaded );
		}
		
		/*
		for ( String recipient : MiniMaxi.RECIPIENTS ) {
			
			List<LocationDTO> adventures = this.adventurer.read( recipient );
			
			map.put( recipient, adventures );
			
		}
		*/
			
		for ( Location location: locations ) {
			
			Result<LocationDTO> individualResult
				= new Result<LocationDTO>();
			
			LocationDTO dto 
				= Location.dto( location );
			
			dto.setColor( VeloUtil.color( location) );
			
			individualResult.setValue( Value.OK );
			individualResult.setObject( dto );
			
			individualResults.add( individualResult );
			
		}
			
		result.setValue( Value.OK );
		result.setObject( individualResults );
		
		return result;
			
	}
	
	@Transactional( readOnly=false )
	public Result<List<LocationDTO>> consume( List<LocationDTO> locations ) {
		
		logger.info( "[{}]; consume");
		
		Date now
			= new Date();
		
		Result<List<LocationDTO>> result
			= new Result<List<LocationDTO>>();
		
		List<LocationDTO> consumed
			= list();
		
		result.setValue( Value.OK );
		
		for ( LocationDTO location : locations ) {
			
			Location l
				= Location.from( location );
			
			l.setMoment( now );
			
			Location created 
				= this.locationManager.create( l );
			
			consumed.add( Location.dto( created ) );
			
		}
		
		result.setObject( consumed );
		
		return result;
		
	}
	
	public LocationService guard() {
		this.dataGuard.guard();
		return this;
	}

}
