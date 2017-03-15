package be.occam.velo.domain.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import be.occam.utils.spring.web.Result;
import be.occam.utils.spring.web.Result.Value;
import be.occam.velo.application.util.DataGuard;
import be.occam.velo.domain.object.Map;
import be.occam.velo.domain.people.Mapper;

public class MapService {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	DataGuard dataGuard;
	
	@Resource
	Mapper mapper;
	
	public MapService( ) {
		logger.info( "location service started" );
	}
	
	@Transactional( readOnly=true )
	public Result<MapDTO> findOneByUserID( String userID ) {
		
		logger.info( "findOneByUserID, userID is [{}]", userID );
		
		Result<MapDTO> result
			= new Result<MapDTO>();
		
		Map map
			= this.mapper.findOneByUserID( userID );
		
		result.setValue( Value.OK );
		result.setObject( Map.dto( map )  );
		
		return result;
			
	}
	
	@Transactional( readOnly=false )
	public Result<MapDTO> consume( MapDTO map ) {
		
		logger.info( "[{}]; consume");
		
		Result<MapDTO> result
			= new Result<MapDTO>();
		
		result.setValue( Value.OK );
		
		Map consumed
			= null;
		
		Map loaded
			= this.mapper.findOneByUserID( map.getUserID() );
		
		if ( loaded == null ) {
			consumed = this.mapper.create( Map.from( map ) );
		}
		else {
			consumed = this.mapper.update( map.getUuid(), Map.from( map ) );
		}
		
		result.setObject( Map.dto( consumed ) );
		
		return result;
		
	}
	
	public MapService guard() {
		this.dataGuard.guard();
		return this;
	}

}
