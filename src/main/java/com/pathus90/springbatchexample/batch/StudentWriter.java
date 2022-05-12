package com.pathus90.springbatchexample.batch;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import com.pathus90.springbatchexample.model.Student;
import com.pathus90.springbatchexample.service.IStudentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentWriter implements ItemWriter<Student> {

    private final IStudentService studentService;

    @Override
    public void write(List<? extends Student> students) {
        students.forEach(student -> {
            log.info("Enregistrement en base de l'objet {}", student);
            studentService.insertStudent(student);
        });
    }


}
