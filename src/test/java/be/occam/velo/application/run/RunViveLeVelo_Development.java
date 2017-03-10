package be.occam.velo.application.run;

import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

public class RunViveLeVelo_Development extends JTest {
	
	public RunViveLeVelo_Development() {
		super( "/", 8067, ConfigurationProfiles.DEV );
	}
		
	@Test
	public void doesItSmoke() throws Exception {
		
		System.setSecurityManager( null );
		Thread.sleep( 10000000 );
		
	}

}
