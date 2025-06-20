package com.example.tesastudentsamuel.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentStateOfOrigin;
    private int studentAge;
    private String studentStatus;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}