package com.example.tesastudentsamuel.controller;

import com.example.tesastudentsamuel.exception.StudentNotFoundException;
import com.example.tesastudentsamuel.model.request.student.StudentCreateRequest;
import com.example.tesastudentsamuel.model.request.student.StudentUpdateRequest;
import com.example.tesastudentsamuel.model.response.StudentResponse;
import com.example.tesastudentsamuel.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated  // enable @Min on @RequestParam
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create-student")
    public ResponseEntity<String> createStudent(
            @Valid @RequestBody StudentCreateRequest request
    ) {
        studentService.createStudent(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Student created successfully");
    }

    @PostMapping("/update-student")
    public ResponseEntity<String> updateStudent(
            @Valid @RequestBody StudentUpdateRequest request
    ) {
        try {
            studentService.updateStudent(request);
            return ResponseEntity.ok("Student updated successfully");
        } catch (StudentNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

    @GetMapping("/get-student-by-id")
    public ResponseEntity<StudentResponse> getStudentById(
            @RequestParam @Min(value = 1, message = "id must be ≥ 1") int id
    ) {
        try {
            StudentResponse resp = studentService.getStudentById(id);
            return ResponseEntity.ok(resp);
        } catch (StudentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-students")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/get-students-by-firstname")
    public ResponseEntity<List<StudentResponse>> getStudentByFirstName(
            @RequestParam String firstName
    ) {
        List<StudentResponse> resp = studentService.getStudentByFirstName(firstName);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/delete-student")
    public ResponseEntity<?> deleteStudentById(
            @RequestParam @Min(value = 1, message = "id must be ≥ 1") int id
    ) {
        try {
            List<StudentResponse> updatedList = studentService.softDeleteStudent(id);
            return ResponseEntity.ok(updatedList);
        } catch (StudentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

    // Optional: handle any other uncaught StudentNotFoundException
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleNotFound(StudentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
