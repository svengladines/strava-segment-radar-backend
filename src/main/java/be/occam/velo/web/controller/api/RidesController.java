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
import be.occam.velo.RideDTO;
import be.occam.velo.domain.service.RideService;

@Controller
@RequestMapping(value="/rides")
public class RidesController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( RidesController.class );
	
	@Resource
	RideService rideService;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<List<RideDTO>> query( @RequestParam( required=false ) Boolean today, WebRequest request ) {
		
		logger.debug( "GET; today = [{}]", today );
		
		Result<List<Result<RideDTO>>> ridesResult
			= rideService.guard().query( today );
		
		List<RideDTO> rides
			= list();
		
		for ( Result<RideDTO> result : ridesResult.getObject() ) {
			
			rides.add( result.getObject() );
			
		}
		
		return response( rides, HttpStatus.OK );
			
	}
	
	@RequestMapping( method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
	@ResponseBody
	public ResponseEntity<List<RideDTO>> post( @RequestBody List<RideDTO> rides, WebRequest request ) {
		
		logger.info( "rides received: [{}]", rides );
		
		HttpHeaders httpHeaders
			= new HttpHeaders();

		httpHeaders.add("Access-Control-Allow-Origin", "*" ) ;
		httpHeaders.add("Access-Control-Allow-Methods", "GET,OPTIONS" );
		httpHeaders.add("Access-Control-Allow-Credentials","true");
		
		this.rideService.guard().consume( rides );

		ResponseEntity<List<RideDTO>> response
			= new ResponseEntity<List<RideDTO>>( rides , httpHeaders, HttpStatus.OK );


		return response;

}
	
}
