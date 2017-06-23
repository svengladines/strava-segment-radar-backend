package be.occam.velo.application.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.datanucleus.api.jpa.PersistenceProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import be.occam.utils.spring.configuration.ConfigurationProfiles;
import be.occam.velo.application.util.DataGuard;
import be.occam.velo.application.util.NoopGuard;
import be.occam.velo.application.util.ProductionData;
import be.occam.velo.domain.people.LocationManager;
import be.occam.velo.domain.people.Mapper;
import be.occam.velo.domain.people.RideManager;
import be.occam.velo.domain.service.LocationService;
import be.occam.velo.domain.service.MapService;
import be.occam.velo.domain.service.RideService;

@Configuration
public class ViveLeVeloApplicationConfig {
	
	final static Logger logger
		= LoggerFactory.getLogger( ViveLeVeloApplicationConfig.class );

	final static String BASE_PKG 
		= "be.occam.velo";
	
	static class propertiesConfigurer {
		
		@Bean
		@Scope("singleton")
		public static PropertySourcesPlaceholderConfigurer propertiesConfig() {
			return new PropertySourcesPlaceholderConfigurer();
		}
		
	}
	
	@Configuration
	@Profile({ConfigurationProfiles.PRODUCTION})
	static class DomainConfigForProduction {
		
		@Bean
		DataGuard dataGuard() {
			
			return new NoopGuard();
			
		}
		
		@Bean
		String svekkesEmailAddress() {
			
			return "sven.gladines@gmail.com"; 
			
		}
		
	}
	
	@Configuration
	@EnableTransactionManagement
	public static class DomainConfigShared {
		
		/*
		@Bean
		public MailMan mailMan() {
			return new MailMan();
		}
		
		@Bean
		public JavaMailSender javaMailSender () {
			
			JavaMailSenderImpl sender
				= new JavaMailSenderImpl();
			return sender;
			
		}
		*/
		
		@Bean
		public LocationService locationService(  ) {
			return new LocationService(  );
		}
		
		@Bean
		public MapService mapService(  ) {
			return new MapService(  );
		}
		
		@Bean
		public RideService rideService() {
			return new RideService();
		}
		
		@Bean
		public LocationManager locationManager() {
			return new LocationManager();
		}
		
		@Bean
		public RideManager rideManager() {
			return new RideManager();
		}
		
		@Bean
		public Mapper mapper() {
			return new Mapper();
		}
		
		/*
		@Bean
		FTPClient ftpClient( @Value("${ftp.user}") String ftpUser, @Value("${ftp.password}") String ftpPassword ) {  
			return new FTPClient( "ftp.debrodders.be", ftpUser, ftpPassword );
		}
		*/
		
	}
	
		@Configuration
		@Profile(ConfigurationProfiles.PRODUCTION)
		@EnableJpaRepositories(BASE_PKG)
		static class EntityManagerConfigForProduction {
			
			@Bean
			public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(PersistenceProvider persistenceProvider ) {
				
				LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
				factory.setPackagesToScan( BASE_PKG );
				factory.setPersistenceProvider( persistenceProvider );
				// factory.setDataSource(jpaDataSource);
				factory.setPersistenceUnitName("minimaxi-backend-production");
				factory.getJpaPropertyMap().put( "datanucleus.jpa.addClassTransformer", "false" );
				factory.getJpaPropertyMap().put( "datanucleus.appengine.datastoreEnableXGTransactions", "true" );
				factory.getJpaPropertyMap().put( "datanucleus.metadata.allowXML", "false" );
				factory.afterPropertiesSet();
				return factory;
			}
			
			@Bean
			PersistenceProvider persistenceProvider() {
				
				PersistenceProviderImpl provider
					= new PersistenceProviderImpl();
				
				return provider;
				
			}

			@Bean
			public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean factory) {
				return factory.getObject();
			}

			@Bean
			public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
				return new PersistenceExceptionTranslationPostProcessor();
			}

			@Bean
			public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
				JpaTransactionManager transactionManager = new JpaTransactionManager();
				transactionManager.setEntityManagerFactory(entityManagerFactory);
				return transactionManager;
			}
			
			@Bean
			DataGuard dataGuard() {
				
				return new NoopGuard();
				
			}
			
		}
		
		@Configuration
		@Profile( { ConfigurationProfiles.PRODUCTION,ConfigurationProfiles.DEV } )
		public static class Data {
			
			@Bean
			@Lazy( false )
			ProductionData productionData() {
				return new ProductionData();
			}
			
		}

	
}