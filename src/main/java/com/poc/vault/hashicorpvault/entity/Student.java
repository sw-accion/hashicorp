package com.poc.vault.hashicorpvault.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "poc_student_test")
@Data
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_number")
	private int roleNumber;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "student_age")
	private int studentAge;

	@Column(name = "gender")
	private String gender;

}
