package be.occam.velo.application.jtests;

import static be.occam.utils.spring.web.Client.*;
import static be.occam.velo.application.util.TestUtil.*;
import org.junit.Before;
import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.web.Client;
import be.occam.velo.RideDTO;

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
		
		RideDTO consumed
			= ride( postJSON( this.ridesURL, new RideDTO[] { one } ) );
		
		logger.info( "consumed ride has title [{}]", consumed.getTitle() );
		
	}
	

}
