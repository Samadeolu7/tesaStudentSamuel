package com.example.tesastudentsamuel.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {
    private int studentId;
    private String firstName;
    private String lastName;
    private String stateOfOrigin;
    private int age;
    private String createdAt;
    private String updatedAt;
}
