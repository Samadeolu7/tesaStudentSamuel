package com.example.tesastudentsamuel.model.request.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentUpdateRequest {
    @NotNull(message = "studentId is required")
    @Min(value = 100, message = "studentId must be ≥ 1")
    private Integer studentId;

    private String firstName;

    private String lastName;

    private String stateOfOrigin;

    private int age;
}
