package be.occam.velo.application.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.datanucleus.api.jpa.PersistenceProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import be.occam.utils.spring.configuration.ConfigurationProfiles;
import be.occam.velo.application.jtests.DevData;
import be.occam.velo.application.util.DataGuard;
import be.occam.velo.application.util.DevGuard;

import com.google.appengine.tools.development.testing.LocalAppIdentityServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMailServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.apphosting.api.ApiProxy;

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
	@EnableJpaRepositories( JPA_PKG )
	@Profile( { ConfigurationProfiles.DEV } )
	static class EntityManagerConfig {
		
		@Bean
		public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(PersistenceProvider persistenceProvider, LocalServiceTestHelper dataStoreHelper ) {
			
			LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
			factory.setPackagesToScan( JPA_PKG );
			factory.setPersistenceProvider( persistenceProvider );
			// factory.setDataSource(jpaDataSource);
			factory.setPersistenceUnitName("minimaxi-jpa-dev-test");
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