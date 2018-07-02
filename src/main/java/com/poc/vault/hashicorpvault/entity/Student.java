package com.poc.vault.hashicorpvault.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "STUDENT1")
@Data
public class Student {

	@Id
	@Column(name="id")
	private int id;

	@Column(name = "role_number")
	private int roleNumber;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "student_age")
	private int studentAge;

	@Column(name = "gender")
	private String gender;

	
	

}
