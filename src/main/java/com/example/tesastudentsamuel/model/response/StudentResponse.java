package com.example.tesastudentsamuel.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {
    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentStateOfOrigin;
    private int studentAge;
    private String createdAt;
    private String updatedAt;
}
