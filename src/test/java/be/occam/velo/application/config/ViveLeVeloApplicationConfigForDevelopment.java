package be.occam.velo.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import com.google.appengine.tools.development.testing.LocalAppIdentityServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMailServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.apphosting.api.ApiProxy;

import be.occam.utils.spring.configuration.ConfigurationProfiles;
import be.occam.velo.application.jtests.DevData;
import be.occam.velo.application.util.DataGuard;
import be.occam.velo.application.util.DevGuard;

@Configuration
public class ViveLeVeloApplicationConfigForDevelopment {
	
	final static String JPA_PKG 
		= "be.occam.velo.repository";

	@Profile( { ConfigurationProfiles.DEV } )
	public static class DbConfigForDev {
		
		@Bean
		String acsiEmailAddress() {
			
			return "sven.gladines@gmail.com"; 
			
		}
		
		@Bean
		@Lazy( false )
		public DevData devData() {
			return new DevData();
		}
		
	}
		
	@Configuration
	@Profile( { ConfigurationProfiles.DEV } )
	public static class LocalServiceConfig {
		
		@Bean
		public LocalServiceTestHelper helper() {
			
			LocalServiceTestHelper helper
				= new LocalServiceTestHelper( new LocalAppIdentityServiceTestConfig(), new LocalDatastoreServiceTestConfig().setApplyAllHighRepJobPolicy(), new LocalMailServiceTestConfig() );
			helper.setUp();
			
			return helper;
			
		}
		
		@Bean
		DataGuard dataGuard( LocalServiceTestHelper helper ) {
			
			return new DevGuard( ApiProxy.getCurrentEnvironment() );
			
		}
		
	}
	
}