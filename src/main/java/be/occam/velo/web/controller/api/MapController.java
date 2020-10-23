package be.occam.velo.web.controller.api;

import static be.occam.utils.spring.web.Controller.response;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import be.occam.utils.spring.web.Result;
import be.occam.velo.domain.service.MapService;
import be.occam.velo.web.dto.MapDTO;

@Controller
@RequestMapping(value="/maps/{userID}")
public class MapController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( MapController.class );
	
	@Resource
	MapService mapService;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<Result<MapDTO>> retrieve( @RequestParam( required=false ) String userID, WebRequest request ) {
		
		logger.debug( "GET; query for user [{}]", userID );
		
		Result<MapDTO> mapResult
			= mapService.guard().findOneByUserID( userID );
		
		return response( mapResult, HttpStatus.OK );
			
	}
	
	@RequestMapping( method = { RequestMethod.PUT }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
	@ResponseBody
	public ResponseEntity<MapDTO> update( @RequestBody MapDTO map, WebRequest request ) {
		
		logger.info( "map received: [{}]", map );
		
		HttpHeaders httpHeaders
			= new HttpHeaders();

		httpHeaders.add("Access-Control-Allow-Origin", "*" ) ;
		httpHeaders.add("Access-Control-Allow-Methods", "GET,OPTIONS" );
		httpHeaders.add("Access-Control-Allow-Credentials","true");
		
		this.mapService.guard().consume( map );

		ResponseEntity<MapDTO> response
			= new ResponseEntity<MapDTO>( map , httpHeaders, HttpStatus.OK );


		return response;

}
	
}
