package com.poc.vault.hashicorpvault.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
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
     public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(
         EntityManagerFactoryBuilder builder, @Qualifier("mySqlDataSource") DataSource dataSource) {
       return builder.dataSource(dataSource).packages("com.poc.vault.hashicorpvault.entity").persistenceUnit("student")
           .build();
     }

     @Primary
     @Bean(name = "mySqlTransactionManager")
     public PlatformTransactionManager mySqlTransactionManager(
         @Qualifier("mySqlEntityManagerFactory") EntityManagerFactory mySqlEntityManagerFactory) {
       return new JpaTransactionManager(mySqlEntityManagerFactory);
     }
}
