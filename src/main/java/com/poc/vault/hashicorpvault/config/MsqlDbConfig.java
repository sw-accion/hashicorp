package com.poc.vault.hashicorpvault.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "mySqlEntityManagerFactory",
transactionManagerRef = "mySqlTransactionManager", basePackages = {"com.poc.vault.hashicorpvault.mysql.repository"})
@EnableTransactionManagement
public class MsqlDbConfig {
	
	@Autowired
    private Environment env;
    private String username;
    private String password;
    
    @Primary
    @Bean(name = "mySqlDataSource")
  	public DataSource db1Datasource() {
  		username = env.getProperty("example.username");
    	password = env.getProperty("example.password"); 
  		DriverManagerDataSource dataSource = new DriverManagerDataSource();
  		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
  		dataSource.setUrl("jdbc:mysql://localhost:3306/db_example?useSSL=false");
  		dataSource.setUsername(username);
  		dataSource.setPassword(password);
  		return dataSource;
  	}
     
    @Primary
    @Bean(name = "mySqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postGreEntityManagerFactory() {
   	    LocalContainerEntityManagerFactoryBean em
   	      = new LocalContainerEntityManagerFactoryBean();
   	    em.setDataSource(db1Datasource());
   	    em.setPackagesToScan("com.poc.vault.hashicorpvault.entity");
   	    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
   	    if (additionalProperties() != null) {
   	        em.setJpaProperties(additionalProperties());
   	    }
   	    return em;
   	}


     @Primary
     @Bean(name = "mySqlTransactionManager")
     public PlatformTransactionManager mySqlTransactionManager(
         @Qualifier("mySqlEntityManagerFactory") EntityManagerFactory mySqlEntityManagerFactory) {
       return new JpaTransactionManager(mySqlEntityManagerFactory);
     }
     
     private Properties additionalProperties() {
 	    Properties hibernateProperties = new Properties();
 	    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", 
 	      env.getProperty("mysql-hibernate.hbm2ddl.auto"));
 	    hibernateProperties.setProperty("hibernate.dialect", 
 	      env.getProperty("mysql-hibernate.dialect"));
 	   hibernateProperties.setProperty("hibernate.show_sql", 
	    		env.getProperty("mysql-hibernate.show_sql"));
 	    return hibernateProperties;
 	}
}
