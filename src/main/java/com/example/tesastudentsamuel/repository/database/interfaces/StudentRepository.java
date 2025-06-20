package com.example.tesastudentsamuel.repository.database.interfaces;

import com.example.tesastudentsamuel.model.entity.Student;

import java.util.List;

public interface StudentRepository {

    int createStudent(Student student);

    Student getStudentById(int studentId);

    List<Student> getStudentByFirstName(String firstName);

    List<Student> getAllStudents();

    List<Student> search(String query);

    int updateStudent(Student student);

    int deleteStudent(int studentId);

}
