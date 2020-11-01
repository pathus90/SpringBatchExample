package com.pathus90.springbatchexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathus90.springbatchexample.model.Student;
import com.pathus90.springbatchexample.repository.IStudentRepository;
import com.pathus90.springbatchexample.service.IStudentService;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public void insertStudent(Student student) {
        studentRepository.save(student);
    }
}
