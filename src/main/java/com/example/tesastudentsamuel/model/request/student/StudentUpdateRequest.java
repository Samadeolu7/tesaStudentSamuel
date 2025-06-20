package com.example.tesastudentsamuel.model.request.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentUpdateRequest {
    @NotNull(message = "studentId is required")
    @Min(value = 100, message = "studentId must be ≥ 100")
    private int studentId;

    private String studentFirstName;

    private String studentLastName;

    private String studentStateOfOrigin;

    private int studentAge;

    private String studentStatus;
}
