package com.poc.vault.hashicorpvault.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "postGreEntityManagerFactory",
transactionManagerRef = "postGreTransactionManager", basePackages = {"com.poc.vault.hashicorpvault.postgres.repository"})
@EnableTransactionManagement
public class PostgreDb {
	
	@Autowired
    private Environment env;
    private String username;
    private String password;
    
    
    @Bean(name = "postGreDataSource")
  	public DataSource db2Datasource() {
    	username = env.getProperty("example.username");
     	password = env.getProperty("example.password"); 
   		DriverManagerDataSource dataSource = new DriverManagerDataSource();
   		dataSource.setDriverClassName("org.postgresql.Driver");
   		dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?useSSL=false");
   		dataSource.setUsername(username);
   		dataSource.setPassword(password);
  		return dataSource;
  	}
     
   
     @Bean(name = "postGreEntityManagerFactory")
     public LocalContainerEntityManagerFactoryBean postGreEntityManagerFactory() {
    	    LocalContainerEntityManagerFactoryBean em
    	      = new LocalContainerEntityManagerFactoryBean();
    	    em.setDataSource(db2Datasource());
    	    em.setPackagesToScan("com.poc.vault.hashicorpvault.entity");
    	    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    	    if (additionalProperties() != null) {
    	        em.setJpaProperties(additionalProperties());
    	    }
    	    return em;
    	}


     @Bean(name = "postGreTransactionManager")
     public PlatformTransactionManager postGreTransactionManager(
         @Qualifier("postGreEntityManagerFactory") EntityManagerFactory postGreEntityManagerFactory) {
       return new JpaTransactionManager(postGreEntityManagerFactory);
     }
     
     private Properties additionalProperties() {
    	    Properties hibernateProperties = new Properties();
    	    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", 
    	      env.getProperty("postgres-hibernate.hbm2ddl.auto"));
    	    hibernateProperties.setProperty("hibernate.dialect", 
    	    		env.getProperty("postgres-hibernate.dialect"));
    	    hibernateProperties.setProperty("hibernate.show_sql", 
    	    		env.getProperty("postgres-hibernate.show_sql"));
    	    hibernateProperties.setProperty("hibernate.temp.use_jdbc_metadata_defaults",
    	    		env.getProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults"));
    	    return hibernateProperties;
    	}

}
