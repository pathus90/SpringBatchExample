package com.pathus90.springbatchexample.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.pathus90.springbatchexample.model.Student;
import com.pathus90.springbatchexample.repository.IStudentRepository;
import com.pathus90.springbatchexample.service.IStudentService;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;

    @Override
    public void insertStudent(Student student) {
        studentRepository.save(student);
    }
}
