package be.occam.velo.web.controller.api;

import static be.occam.utils.javax.Utils.list;
import static be.occam.utils.spring.web.Controller.response;

import java.util.List;

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
import be.occam.velo.LocationDTO;
import be.occam.velo.domain.service.LocationService;

@Controller
@RequestMapping(value="/locations")
public class LocationsController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( LocationsController.class );
	
	@Resource
	LocationService locationService;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<List<LocationDTO>> query( @RequestParam( required=false ) String userID, WebRequest request ) {
		
		logger.debug( "GET; query for user [{}]", userID );
		
		Result<List<Result<LocationDTO>>> locationsResult
			= locationService.guard().query( userID );
		
		List<LocationDTO> locations
			= list();
		
		for ( Result<LocationDTO> result : locationsResult.getObject() ) {
			
			locations.add( result.getObject() );
			
		}
		
		return response( locations, HttpStatus.OK );
			
	}
	
	@RequestMapping( method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
	@ResponseBody
	public ResponseEntity<List<LocationDTO>> post( @RequestBody List<LocationDTO> locations, WebRequest request ) {
		
		logger.info( "locations received: [{}]", locations );
		
		HttpHeaders httpHeaders
			= new HttpHeaders();

		httpHeaders.add("Access-Control-Allow-Origin", "*" ) ;
		httpHeaders.add("Access-Control-Allow-Methods", "GET,OPTIONS" );
		httpHeaders.add("Access-Control-Allow-Credentials","true");
		
		this.locationService.guard().consume( locations );

		ResponseEntity<List<LocationDTO>> response
			= new ResponseEntity<List<LocationDTO>>( locations , httpHeaders, HttpStatus.OK );


		return response;

}
	
}
