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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import be.occam.utils.spring.web.Result;
import be.occam.velo.RideDTO;
import be.occam.velo.domain.service.RideService;
import be.occam.velo.web.util.VeloUtil;

@Controller
@RequestMapping(value="/rides/{uuid}")
public class RideController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( RideController.class );
	
	@Resource
	RideService rideService;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<Result<RideDTO>> get( @PathVariable String uuid, WebRequest request ) {
		
		logger.debug( "GET; uuid = [{}]", uuid );
		
		Result<RideDTO> rideResult 
			= null;
		
		if ( "x".equals( uuid ) ) {
			rideResult = rideService.guard().findOneByTitle( VeloUtil.DUVELKE_START_TITLE );
		}
		else {
			rideResult= rideService.guard().findOne( uuid );
		}
		
		return response( rideResult, HttpStatus.OK );
			
	}
	
	@RequestMapping( method = { RequestMethod.PUT }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
	@ResponseBody
	public ResponseEntity<Result<RideDTO>> put( @RequestBody RideDTO ride, WebRequest request ) {
		
		logger.info( "ride received: [{}]", ride );
		
		HttpHeaders httpHeaders
			= new HttpHeaders();

		httpHeaders.add("Access-Control-Allow-Origin", "*" ) ;
		httpHeaders.add("Access-Control-Allow-Methods", "POST" );
		httpHeaders.add("Access-Control-Allow-Credentials","true");
		
		Result<List<RideDTO>> consumeResult
			= this.rideService.guard().consume( list( ride ) );
		
		Result<RideDTO> result
			= new Result<RideDTO>();
		
		// TODO, check size
		result.setValue( consumeResult.getValue() );
		result.setObject( consumeResult.getObject().get( 0 ) );
		result.setErrorCode( consumeResult.getErrorCode() );

		ResponseEntity<Result<RideDTO>> response
			= new ResponseEntity<Result<RideDTO>>( result , httpHeaders, HttpStatus.OK );

		return response;

	}
	
	@RequestMapping( method = { RequestMethod.DELETE } )
	@ResponseBody
	public ResponseEntity<Result<RideDTO>> delete( @PathVariable String uuid, WebRequest request ) {
		
		logger.info( "delete request received for ride [{}]", uuid );
		
		HttpHeaders httpHeaders
			= new HttpHeaders();

		httpHeaders.add("Access-Control-Allow-Origin", "*" ) ;
		httpHeaders.add("Access-Control-Allow-Credentials","true");
		
		Result<RideDTO> result
			= this.rideService.guard().delete( uuid );
		
		ResponseEntity<Result<RideDTO>> response
			= new ResponseEntity<Result<RideDTO>>( result , httpHeaders, HttpStatus.OK );

		return response;

	}
	
	@RequestMapping( value="/**", method = { RequestMethod.OPTIONS } )
	@ResponseBody
	public ResponseEntity<String> options() {

		logger.info( "options!!" );
	
		HttpHeaders httpHeaders
			= new HttpHeaders();
		
		httpHeaders.add("Access-Control-Allow-Origin", "*" ) ;
		httpHeaders.add("Access-Control-Allow-Methods", "OPTIONS,GET,POST,PUT,DELETE" );
		httpHeaders.add("Access-Control-Allow-Credentials","true");
		httpHeaders.add("Access-Control-Allow-Headers","Content-Type");
		
		ResponseEntity<String> response;
		
		response = new ResponseEntity<String>( "okelidokeli", httpHeaders, HttpStatus.OK );
		
		return response;
	}
	
	
	
}
