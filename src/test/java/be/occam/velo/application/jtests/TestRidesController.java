package be.occam.velo.application.jtests;

import static be.occam.utils.spring.web.Client.postJSON;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import be.occam.test.jtest.JTest;
import be.occam.velo.CoordinateDTO;
import be.occam.velo.RideDTO;
import be.occam.velo.application.util.TestUtil.RidesResult;

public class TestRidesController extends JTest {
	
	protected String ridesURL;
	
	public TestRidesController() {
		
		super("/vive");
		
	}
	
	@Before
	public void setUp() {
		super.setUp();
		this.ridesURL = super.baseAPIURL().append( "/rides" ).toString();
	}
	
	@Test
	public void testPost() {
		
		RideDTO one
			= new RideDTO();
		
		CoordinateDTO start
			= new CoordinateDTO();
		start.setLatitude( 50.0D );
		start.setLongitude( 50.0D );
		
		one.setStart( start );
		
		postJSON( this.ridesURL, new RideDTO[] { one } );
		
		//logger.info( "consumed ride has title [{}]", consumed.getTitle() );
		
	}
	

}
