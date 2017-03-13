package be.occam.velo.application.jtests.scenario;

import org.junit.Before;
import org.junit.Test;

import static be.occam.velo.application.util.TestUtil.*;
import static org.junit.Assert.*;

import be.occam.test.jtest.JTest;
import be.occam.velo.LocationDTO;
import be.occam.velo.application.jtests.TestData.Ids;

/**
 * Reini updates his location twice, Krikke notices the location change
 * 
 * @author sven
 *
 */
public class TestUpdateAndRequest extends JTest {
	
	protected String locationsURL;
	
	public TestUpdateAndRequest() {
		
		super("/vive");
		
	}
	
	@Before
	public void setUp() {
		super.setUp();
		locationsURL( super.baseAPIURL().append( "/locations" ).toString() );
	}
	
	@Test
	public void doesItSmoke() {
		
		double longi = 50D;
		double latti = 50D;
		
		update( Ids.REINI, longi, latti, as( Ids.REINI ) );
		
		LocationDTO reini
			= reini( retrieve( as( Ids.KRIKKE ) ) );
		
		assertEquals( longi, reini.getLongitude(), 0.001D );
		assertEquals( latti, reini.getLattitude(), 0.001D );
		
	}
	

}
