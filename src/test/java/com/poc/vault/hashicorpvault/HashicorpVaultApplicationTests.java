package com.poc.vault.hashicorpvault;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.MBeanExportConfiguration.SpecificPlatform;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import com.poc.vault.hashicorpvault.config.PostgreDb;

@RunWith(SpringRunner.class)
@SpringBootTest( 
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = HashicorpVaultApplication.class
		)
@AutoConfigureMockMvc
@ContextConfiguration(classes=PostgreDb.class)
public class HashicorpVaultApplicationTests {

	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void contextLoads()  throws Exception{
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/students/")
				.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		
		//Verify()
				
				
	}

}