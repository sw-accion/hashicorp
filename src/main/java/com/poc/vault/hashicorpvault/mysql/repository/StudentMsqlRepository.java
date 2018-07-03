package com.poc.vault.hashicorpvault.mysql.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.vault.hashicorpvault.entity.Student;
@Repository
public interface StudentMsqlRepository extends JpaRepository<Student, Long> {

}
