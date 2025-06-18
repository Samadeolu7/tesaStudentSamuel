package com.example.tesastudentsamuel.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private int studentId;
    private String firstName;
    private String lastName;
    private String stateOfOrigin;
    private int age;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean deleted;
}