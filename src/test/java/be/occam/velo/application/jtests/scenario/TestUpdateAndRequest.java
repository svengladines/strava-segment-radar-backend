package be.occam.velo.application.jtests.scenario;

import static be.occam.velo.application.util.TestUtil.as;
import static be.occam.velo.application.util.TestUtil.locationsURL;
import static be.occam.velo.application.util.TestUtil.reini;
import static be.occam.velo.application.util.TestUtil.retrieve;
import static be.occam.velo.application.util.TestUtil.update;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

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
		
		double longi1 = 50D;
		double latti1 = 50D;
		
		update( Ids.REINI, longi1, latti1, as( Ids.REINI ) );
		
		double longi2 = 50D;
		double latti2 = 50D;
		
		update( Ids.REINI, longi2, latti2, as( Ids.REINI ) );
		
		LocationDTO reini
			= reini( retrieve( as( Ids.KRIKKE ) ) );
		
		assertEquals( longi2, reini.getLongitude(), 0.001D );
		assertEquals( latti2, reini.getLattitude(), 0.001D );
		
	}
	

}
