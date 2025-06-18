package com.example.tesastudentsamuel.model.request.student;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentGetRequest {
    private String studentId;
    private String firstName;
    private String lastName;
    private String stateOfOrigin;
    private int age;

}
