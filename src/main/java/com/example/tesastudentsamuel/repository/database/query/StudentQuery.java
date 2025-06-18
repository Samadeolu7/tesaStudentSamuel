package com.example.tesastudentsamuel.repository.database.query;

public class StudentQuery {
    public static final String INSERT_STUDENT = """
        INSERT INTO TESA_SAMUEL_Student
          (firstName, lastName, stateOfOrigin, age)
        VALUES
          (:firstName, :lastName, :stateOfOrigin, :age)
    """;

    public static final String GET_ALL_STUDENTS = """
        SELECT *
          FROM TESA_SAMUEL_Student
         WHERE deleted = 0
    """;

    public static final String GET_STUDENT_BY_ID = """
        SELECT *
          FROM TESA_SAMUEL_Student
         WHERE studentId = :studentId
           AND deleted   = 0
    """;

    public static final String GET_STUDENT_BY_FIRSTNAME = """
        SELECT *
          FROM TESA_SAMUEL_Student
         WHERE firstName = :firstName
           AND deleted   = 0
    """;

    public static final String UPDATE_STUDENT = """
        UPDATE TESA_SAMUEL_Student
           SET firstName     = COALESCE(:firstName,     firstName)
             , lastName      = COALESCE(:lastName,      lastName)
             , stateOfOrigin = COALESCE(:stateOfOrigin, stateOfOrigin)
             , age           = COALESCE(:age,           age)
         WHERE studentId    = :studentId
           AND deleted      = 0
    """;

    public static final String SOFT_DELETE_STUDENT = """
        UPDATE TESA_SAMUEL_Student
           SET deleted = 1
         WHERE studentId = :studentId
    """;
}
