package com.example.tesastudentsamuel.service;

import com.example.tesastudentsamuel.exception.StudentNotFoundException;
import com.example.tesastudentsamuel.mapper.StudentMapper;
import com.example.tesastudentsamuel.model.entity.Student;
import com.example.tesastudentsamuel.model.request.student.StudentCreateRequest;
import com.example.tesastudentsamuel.model.request.student.StudentUpdateRequest;
import com.example.tesastudentsamuel.model.response.StudentResponse;
import com.example.tesastudentsamuel.repository.database.interfaces.StudentRepository;
import com.google.gson.Gson;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final Gson gson = new Gson();

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public int createStudent(StudentCreateRequest request) {
        Student student = gson.fromJson(gson.toJson(request), Student.class);
        try {
            return studentRepository.createStudent(student);
        } catch (DataAccessException ex) {
            // you could detect duplicates here and throw a custom exception
            throw ex;
        }
    }

    public void updateStudent(StudentUpdateRequest request) {
        Student student = gson.fromJson(gson.toJson(request), Student.class);
        int rows = studentRepository.updateStudent(student);
        if (rows == 0) {
            throw new StudentNotFoundException(
                    "Cannot update – no student with id " + request.getStudentId());
        }
    }

    public StudentResponse getStudentById(int studentId) {
        try {
            Student s = studentRepository.getStudentById(studentId);
            return studentMapper.toResponse(s);
        } catch (EmptyResultDataAccessException ex) {
            throw new StudentNotFoundException(
                    "Student with id " + studentId + " not found", ex);
        }
    }

    public List<StudentResponse> getStudentByFirstName(String firstName) {

        return studentRepository
                .getStudentByFirstName(firstName)
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> search(String query) {

        return studentRepository
                .search(query)
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository
                .getAllStudents()
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Soft‐deletes the given student (sets deleted = true) and
     * then returns the list of remaining students.
     */
    public List<StudentResponse> softDeleteStudent(int studentId) {
        // verify existence first
        getStudentById(studentId);  // will throw if missing

        int rows = studentRepository.deleteStudent(studentId);
        if (rows == 0) {
            throw new StudentNotFoundException(
                    "Cannot delete – no student with id " + studentId);
        }

        return getAllStudents();
    }
}
