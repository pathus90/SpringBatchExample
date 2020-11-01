package com.pathus90.springbatchexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathus90.springbatchexample.model.Student;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
}
