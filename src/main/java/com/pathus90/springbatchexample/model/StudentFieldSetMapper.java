package com.pathus90.springbatchexample.model;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class StudentFieldSetMapper implements FieldSetMapper<Student> {
    @Override
    public Student mapFieldSet(FieldSet fieldSet) {
        return Student.builder()
                .rank(fieldSet.readString(0))
                .firstName(fieldSet.readString(1))
                .lastName(fieldSet.readString(2))
                .center(fieldSet.readString(3))
                .pv(fieldSet.readString(4))
                .origin(fieldSet.readString(5))
                .mention(fieldSet.readString(6))
                .build();
    }
}

