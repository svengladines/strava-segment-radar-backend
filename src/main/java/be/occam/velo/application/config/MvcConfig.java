package be.occam.velo.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import be.occam.velo.web.controller.api.LocationsController;
import be.occam.velo.web.controller.api.MapController;
import be.occam.velo.web.controller.api.RideController;
import be.occam.velo.web.controller.api.RidesController;

@Configuration
@EnableWebMvc
public class MvcConfig {
	
	@Configuration
	public static class DispatcherConfig {
		
		@Bean
		public InternalResourceViewResolver internalResourceViewResolver() {
			InternalResourceViewResolver resolver
				= new InternalResourceViewResolver();
			resolver.setPrefix( "/WEB-INF/jsp/" );
			resolver.setSuffix( ".jsp" );
			return resolver;
		}
		
	}
	
	@Configuration
	public static class ControllerConfig {
		
		/*
		@Bean
		public EntriesController entriesController() {
			
			return new EntriesController();
			
		}
		
		@Bean
		public AdventuresController adventuresController() {
			
			return new AdventuresController();
			
		}
		
		@Bean
		public AdventureController adventureController() {
			
			return new AdventureController();
			
		}
		*/
		
		@Bean
		public LocationsController locationsController() {
			
			return new LocationsController();
			
		}
		
		@Bean
		public RidesController ridesController() {
			
			return new RidesController();
			
		}
		
		@Bean
		public RideController rideController() {
			
			return new RideController();
			
		}
		
		@Bean
		public MapController mapController() {
			
			return new MapController();
			
		}
		
	}
	
	@Configuration
	public static class FormatConfig {
		
		@Bean
		DateFormatter dateFormatter() {
			
			DateFormatter dateFormatter
				= new DateFormatter();
			
			dateFormatter.setPattern("dd/MM/yyyy");
			
			return dateFormatter;
			
		}
	}
	
	/*
	@Bean
	MultipartResolver multipartResolver() {
		
		GMultipartResolver resolver
			= new GMultipartResolver();
		
		return resolver;
		
	}
	*/
		

}
