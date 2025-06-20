package com.example.tesastudentsamuel.model.request.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentCreateRequest {

    @NotBlank(message = "firstName must not be blank")
    private String studentFirstName;

    @NotBlank(message = "lastName must not be blank")
    private String studentLastName;

    @NotBlank(message = "stateOfOrigin must not be blank")
    private String studentStateOfOrigin;

    @Min(value = 0, message = "age must be non-negative")
    private int studentAge;

    private String studentStatus;
}
