package com.poc.vault.hashicorpvault.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.vault.hashicorpvault.entity.Student;
import com.poc.vault.hashicorpvault.service.StudentServiceImpl;

@RestController
public class StudentController {
	
	@Autowired
	private StudentServiceImpl  studentServiceImpl;
	
	
	@GetMapping("/students")
	public List<Student> getStudent(){
		List<Student>  list = studentServiceImpl.getStudents();
		return list;
	}
	
	@PostMapping("/student")
	public Student save(@RequestBody Student std){
		return studentServiceImpl.save(std);
	}

}
