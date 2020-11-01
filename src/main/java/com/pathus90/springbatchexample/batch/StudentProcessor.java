package com.pathus90.springbatchexample.batch;

import org.springframework.batch.item.ItemProcessor;

import com.pathus90.springbatchexample.model.Student;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student student) {
        student.setFirstName(student.getFirstName().toUpperCase());
        student.setLastName(student.getLastName().toUpperCase());
        student.setCenter(student.getCenter().toUpperCase());
        student.setOrigin(student.getOrigin().toUpperCase());
        student.setMention(student.getMention().toUpperCase());
        return student;
    }
}
