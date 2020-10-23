package be.occam.velo.application.util;

import static be.occam.utils.spring.web.Client.getJSON;
import static be.occam.utils.spring.web.Client.postJSON;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.springframework.http.ResponseEntity;

import be.occam.strava.dto.RideDTO;
import be.occam.utils.spring.web.Result;
import be.occam.velo.application.jtests.TestData.Ids;
import be.occam.velo.web.dto.LocationDTO;
import be.occam.velo.web.util.Headers;

public class TestUtil {
	
	protected static String locationsURL;
	
	public static void locationsURL( String url ) {
		locationsURL = url;
	}
	
	public static String locationsURL( ) {
		return locationsURL;
	}
	
	public static class RidesResult extends Result<RideDTO[]> {
		
	};
	
	public static Map<String,String> as( String userId ) {

		Map<String,String> headers
			= new HashMap<String, String>();

		headers.put( Headers.ACTOR, userId );

		return headers;

	}

	public static LocationDTO location( LocationDTO[] locations, String id ) {
		
		LocationDTO location
			= null;
		
		for ( LocationDTO l : locations ) {
			
			if ( id.equals( l.getUserID() ) ) {
				location = l;
				break;
			}
			
		}
		
		if ( location == null ) {
			Assert.fail( String.format( "no location found for id [%s]", id ) );
		}
		
		return location;
		
	}
	
	public static LocationDTO reini( LocationDTO... locations ) {
		
		LocationDTO location
			= location( locations, Ids.REINI );
		
		return location;
		
	}
	
	public static LocationDTO krikke( LocationDTO... locations ) {
		
		LocationDTO location
			= location( locations, Ids.KRIKKE );
		
		return location;
		
	}
	
	public static LocationDTO john( LocationDTO... locations ) {
		
		LocationDTO location
			= location( locations, Ids.JOHN );
		
		return location;
		
	}
	
	public static LocationDTO update( String userID, double longitude, double lattitude, Map<String,String> headers ) {
		
		LocationDTO location
			= new LocationDTO();

		location.setUserID( userID );
		location.setLongitude( longitude );
		location.setLattitude( lattitude );

		LocationDTO[] r
			= new LocationDTO[] {};
	
		ResponseEntity<LocationDTO[]> response
			= (ResponseEntity<LocationDTO[]>) postJSON( locationsURL, new LocationDTO[] { location },  headers );

		return response.getBody()[0];
		
		
	}
	
	public static LocationDTO[] retrieve( Map<String,String> headers ) {
		
		ResponseEntity<LocationDTO[]> response
			= getJSON( locationsURL, LocationDTO[].class, headers );
		
		return response.getBody();
		
		
	}
	
	public static RideDTO ride( RideDTO[] rides ) {
		
		RideDTO ride
			= null;
		
		if ( rides.length > 0 ) {
			ride = rides[ 0 ];
		}
		
		return ride;
		
	}
	
	public static RideDTO ride( ResponseEntity<RidesResult> response ) {
		
		return ride( response.getBody().getObject() );
		
	}
	
	
	public static Long oneHour = Long.valueOf( 60 * 60 * 1000);
	public static Long oneMinute = Long.valueOf( 60 * 1000 );

}
