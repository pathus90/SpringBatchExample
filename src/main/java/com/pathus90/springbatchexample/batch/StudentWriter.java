package com.pathus90.springbatchexample.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.pathus90.springbatchexample.model.Student;
import com.pathus90.springbatchexample.service.IStudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentWriter implements ItemWriter<Student> {

    @Autowired
    private IStudentService studentService;

    @Override
    public void write(List<? extends Student> students) {
        students.forEach(student -> {
            log.info("Enregistrement en base de l'objet {}", student);
            studentService.insertStudent(student);
        });
    }
    
    
}
