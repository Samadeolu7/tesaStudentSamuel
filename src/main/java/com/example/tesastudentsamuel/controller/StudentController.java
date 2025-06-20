package com.example.tesastudentsamuel.controller;

import com.example.tesastudentsamuel.exception.StudentNotFoundException;
import com.example.tesastudentsamuel.model.request.student.StudentCreateRequest;
import com.example.tesastudentsamuel.model.request.student.StudentUpdateRequest;
import com.example.tesastudentsamuel.model.response.ApiResponse;
import com.example.tesastudentsamuel.model.response.StudentResponse;
import com.example.tesastudentsamuel.service.StudentService;
import com.example.tesastudentsamuel.util.ResponseBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create-student")
    public ResponseEntity<ApiResponse<Void>> createStudent(
            @Valid @RequestBody StudentCreateRequest request
    ) {
        studentService.createStudent(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success("Student created successfully", null));
    }

    @PostMapping("/update-student")
    public ResponseEntity<ApiResponse<Void>> updateStudent(
            @Valid @RequestBody StudentUpdateRequest request
    ) {
        studentService.updateStudent(request);
        return ResponseEntity.ok(ResponseBuilder.success("Student updated successfully", null));
    }

    @GetMapping("/get-student-by-id")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(
            @RequestParam @Min(value = 100, message = "id must be ≥ 100") int id
    ) {
        StudentResponse resp = studentService.getStudentById(id);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/get-students")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(ResponseBuilder.success(students));
    }

    @GetMapping("/get-students-by-firstname")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getStudentByFirstName(
            @RequestParam String firstName
    ) {
        List<StudentResponse> resp = studentService.getStudentByFirstName(firstName);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> search(
            @RequestParam String query
    ) {
        List<StudentResponse> resp = studentService.search(query);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @DeleteMapping("/delete-student")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> deleteStudentById(
            @RequestParam @Min(value = 100, message = "id must be ≥ 100") int id
    ) {
        List<StudentResponse> updatedList = studentService.softDeleteStudent(id);
        return ResponseEntity.ok(ResponseBuilder.success(updatedList));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(StudentNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseBuilder.error(ex.getMessage()));
    }
}
