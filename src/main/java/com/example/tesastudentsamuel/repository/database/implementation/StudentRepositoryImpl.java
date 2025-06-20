package com.example.tesastudentsamuel.repository.database.implementation;

import com.example.tesastudentsamuel.model.entity.Student;
import com.example.tesastudentsamuel.repository.database.interfaces.StudentRepository;
import com.example.tesastudentsamuel.repository.database.query.StudentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createStudent( Student student){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("firstName", student.getStudentFirstName())
                .addValue("lastName", student.getStudentLastName())
                .addValue("age", student.getStudentAge())
                .addValue("stateOfOrigin", student.getStudentStateOfOrigin())
                .addValue("status", student.getStudentStatus());

        return jdbcTemplate.update(StudentQuery.INSERT_STUDENT,parameterSource);
    }

    @Override
    public List<Student> getAllStudents() {
        BeanPropertyRowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        return jdbcTemplate.query(StudentQuery.GET_ALL_STUDENTS,rowMapper);
    }

    @Override
    public Student getStudentById(int studentId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("studentId", studentId);
        BeanPropertyRowMapper<Student> studentRowMapper = new BeanPropertyRowMapper<>(Student.class);

        return jdbcTemplate.queryForObject(StudentQuery.GET_STUDENT_BY_ID, parameterSource,studentRowMapper);
    }

    @Override
    public List<Student> getStudentByFirstName(String firstName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("firstName", firstName);
        BeanPropertyRowMapper<Student> studentRowMapper = new BeanPropertyRowMapper<>(Student.class);

        return jdbcTemplate.query(StudentQuery.GET_STUDENT_BY_FIRSTNAME, parameterSource,studentRowMapper);
    }

    @Override
    public List<Student> search(String query) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("query","%"+ query + "%");
        BeanPropertyRowMapper<Student> studentRowMapper = new BeanPropertyRowMapper<>(Student.class);

        return jdbcTemplate.query(StudentQuery.SEARCH_ALL_COLUMNS,parameterSource,studentRowMapper);
    }

    @Override
    public int updateStudent(Student student) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("studentId",    student.getStudentId())
                .addValue("firstName", student.getStudentFirstName())
                .addValue("lastName", student.getStudentLastName())
                .addValue("age", student.getStudentAge())
                .addValue("stateOfOrigin", student.getStudentStateOfOrigin())
                .addValue("status",student.getStudentStatus());
        return jdbcTemplate.update(StudentQuery.UPDATE_STUDENT,parameterSource);
    }

    @Override
    public int deleteStudent(int studentId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("studentId", studentId);

        return jdbcTemplate.update(StudentQuery.SOFT_DELETE_STUDENT,parameterSource);
    }

}