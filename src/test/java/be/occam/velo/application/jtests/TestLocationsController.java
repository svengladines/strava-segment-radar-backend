package be.occam.velo.application.jtests;

import org.junit.Before;
import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.web.Client;
import be.occam.velo.LocationDTO;

public class TestLocationsController extends JTest {
	
	protected String locationsURL;
	
	public TestLocationsController() {
		
		super("/vive");
		
	}
	
	@Before
	public void setUp() {
		super.setUp();
		this.locationsURL = super.baseAPIURL().append( "/locations" ).toString();
	}
	
	@Test
	public void testPost() {
		
		LocationDTO one
			= new LocationDTO();
		
		one.setUserID( "reini" );
		one.setLattitude( 50.0D );
		one.setLongitude( 50.0D );
		
		Client.postJSON( this.locationsURL, new LocationDTO[] { one } );
		
	}
	

}
