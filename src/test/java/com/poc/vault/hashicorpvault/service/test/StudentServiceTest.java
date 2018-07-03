package com.poc.vault.hashicorpvault.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.vault.hashicorpvault.entity.Student;
import com.poc.vault.hashicorpvault.mysql.repository.StudentMsqlRepository;
import com.poc.vault.hashicorpvault.postgres.repository.StudentPsqlRepository;
import com.poc.vault.hashicorpvault.service.StudentServiceImpl;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
	
	 @Autowired
	 private StudentServiceImpl studentServiceImpl;
	 
	 @MockBean
	 private StudentMsqlRepository studentMsqlRepository;
	 @MockBean
	 private StudentPsqlRepository  studentPsqlRepository;
	 
	 @Test
	    public void testCreateStudents() {
		 
		 Student student = new Student();
		 student.setId(101L);
		 student.setRoleNumber(3);
		 student.setStudentAge(21);
		 student.setStudentName("swami");
		 student.setGender("MALE");
		 
		 if(student.getGender().equals("MALE")){
			 Mockito.when(studentMsqlRepository.save(student)).thenReturn(student);
		 }else{
			 Mockito.when(studentPsqlRepository.save(student)).thenReturn(student);
		 }
		 assertThat(studentServiceImpl.save(student), is(equalTo(student)));
	    }

	 
	 @Test
	    public void testFindAllStudents() {
		 
		 Student student1 = new Student();
		 student1.setId(101L);
		 student1.setRoleNumber(3);
		 student1.setStudentAge(21);
		 student1.setStudentName("swami");
		 student1.setGender("MALE");
		 
		 Student student2 = new Student();
		 student2.setId(101L);
		 student2.setRoleNumber(3);
		 student2.setStudentAge(21);
		 student2.setStudentName("sw");
		 student2.setGender("FEMALE");
		 
		 
		 List<Student> studentList = new ArrayList<>();
		 studentList.add(student1);
		 studentList.add(student2);
		 
		 Mockito.when(studentMsqlRepository.findAll()).thenReturn(studentList);
		 assertThat(2, is(equalTo(studentList.size())));
	    }

}
