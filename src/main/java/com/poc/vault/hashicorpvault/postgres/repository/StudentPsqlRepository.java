package com.poc.vault.hashicorpvault.postgres.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poc.vault.hashicorpvault.entity.Student;


@Repository
public interface StudentPsqlRepository extends JpaRepository<Student, Integer> {

}
