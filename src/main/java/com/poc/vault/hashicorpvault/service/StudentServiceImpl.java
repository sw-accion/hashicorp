package com.poc.vault.hashicorpvault.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.vault.hashicorpvault.entity.Student;
import com.poc.vault.hashicorpvault.mysql.repository.StudentMsqlRepository;
import com.poc.vault.hashicorpvault.postgres.repository.StudentPsqlRepository;

@Service
public class StudentServiceImpl {
	
	
	@Autowired
	private StudentMsqlRepository studentMsqlRepository;
	
	@Autowired
	private StudentPsqlRepository studentPsqlRepository;
	
	
	public List<Student> getStudents() {
		List<Student> studentDetails = studentPsqlRepository.findAll();
		return studentDetails;
	}

	public Student save(Student std) {
		if(std.getGender().equals("MALE")){
			return studentMsqlRepository.save(std);
		}
		else{
			return studentPsqlRepository.save(std);
	
		}
	}

}
