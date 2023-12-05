package com.victor.testApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.testApi.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    
}
