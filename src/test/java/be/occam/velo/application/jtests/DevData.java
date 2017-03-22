package be.occam.velo.application.jtests;

import static be.occam.utils.javax.Utils.list;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import be.occam.utils.spring.web.Result;
import be.occam.velo.CoordinateDTO;
import be.occam.velo.LocationDTO;
import be.occam.velo.RideDTO;
import be.occam.velo.domain.service.LocationService;
import be.occam.velo.domain.service.RideService;

public class DevData {
	
	@Resource
	LocationService locationService;
	
	@Resource
	RideService rideService;
	
	public static class Ids {
		
		public static final String REINI = "reini";
		public static final String KRIKKE = "krikke";
		public static final String JOHN = "john";
		
		public static final String STRADE = "strade";
		
	}
	
	public static class Coordinates {
		
		public static final CoordinateDTO SIENA = new CoordinateDTO().setLatitude( 43.333333D ).setLongitude( 11.333333D );
		public static final CoordinateDTO BRUSSELS = new CoordinateDTO().setLatitude( 50.84667D ).setLongitude( 4.35472D );
		
	}
	
	@PostConstruct
	public void injectData() {
		
		{
			RideDTO strade
				= new RideDTO();
			
			strade.setMoment( new Date() );
			strade.setStart( Coordinates.SIENA );
			strade.setTitle( "Strade Bianche" );
			strade.setUuid( Ids.STRADE );
			
			LocationDTO one
				= new LocationDTO();
		
			one.setLattitude( Coordinates.SIENA.getLatitude() );
			one.setLongitude( Coordinates.SIENA.getLongitude() );
			one.setRiderID( Ids.REINI );
			one.setRideID( Ids.STRADE );
			
			LocationDTO two
				= new LocationDTO();
	
			two.setLattitude( 43.4124506D );
			two.setLongitude( 11.1014927D );
			two.setRiderID( Ids.REINI );
			two.setRideID( Ids.STRADE );
			
			LocationDTO three
				= new LocationDTO();

			three.setLattitude( 43.233439D );
			three.setLongitude( 11.1747213D );
			two.setRiderID( Ids.JOHN );
			three.setRideID( Ids.STRADE );
			
			this.locationService.consume( list( one, two, three ) );
			
		}
		
	}

}
