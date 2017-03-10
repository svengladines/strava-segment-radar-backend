package be.occam.velo.domain.service;

import static be.occam.utils.javax.Utils.list;
import static be.occam.utils.javax.Utils.map;

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

public class LocationService {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	DataGuard dataGuard;
	
	public LocationService( ) {
		logger.info( "location service started" );
	}
	
	@Transactional( readOnly=true )
	public Result<List<Result<LocationDTO>>> query( String userID ) {
		
		logger.info( "query, userID is [{}]", userID );
		
		Result<List<Result<LocationDTO>>> result
			= new Result<List<Result<LocationDTO>>>();
		
		List<Result<LocationDTO>> individualResults
			= list();
		
		Map<String, List<LocationDTO>> map
			= map();
		
		/*
		for ( String recipient : MiniMaxi.RECIPIENTS ) {
			
			List<LocationDTO> adventures = this.adventurer.read( recipient );
			
			map.put( recipient, adventures );
			
		}
		*/
		
		for ( String recipient : map.keySet() ) {
			
			if ( userID != null ) {
				
				if ( ! userID.equals( recipient ) ) {
					continue;
				}
				
			}
			
			List<LocationDTO> adventures
				= map.get( recipient );
			
			for ( LocationDTO adventure: adventures ) {
				
				Result<LocationDTO> individualResult
					= new Result<LocationDTO>();
				
				individualResult.setValue( Value.OK );
				individualResult.setObject( adventure );
				
				individualResults.add( individualResult );
				
			}
			
		}
		
		result.setValue( Value.OK );
		result.setObject( individualResults );
		
		return result;
			
	}
	
	@Transactional( readOnly=false )
	public Result<LocationDTO> add( String recipient, LocationDTO adventure ) {
		
		logger.info( "add" );
		
		Result<LocationDTO> result
			= new Result<LocationDTO>();
		
		result.setValue( Value.OK );
		result.setObject( adventure );

		
		return result;
			
	}

	
	public LocationService guard() {
		this.dataGuard.guard();
		return this;
	}

}
