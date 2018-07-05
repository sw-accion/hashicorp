package com.poc.vault.hashicorpvault.repository.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.vault.hashicorpvault.config.MsqlDbConfig;
import com.poc.vault.hashicorpvault.entity.Student;
import com.poc.vault.hashicorpvault.mysql.repository.StudentMsqlRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes=MsqlDbConfig.class)
public class StudentMysqlRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	@Autowired
	private StudentMsqlRepository studentMsqlRepository; 
	
	@Test
	public  void testSavestudent(){
		Student student = getStudent();
		
		Student savedEntity = entityManager.merge(student);
		Student  getFrom = studentMsqlRepository.getOne(savedEntity.getId());
		
		assertThat(getFrom ,is(equalTo(savedEntity)));
	}
	
	
	private Student getStudent(){
		
		Student student = new Student();
		
		student.setId(102L);
		student.setRoleNumber(3);
		student.setStudentAge(21);
		student.setStudentName("swami");
		student.setGender("MALE");
		return student;
		
	}

}
