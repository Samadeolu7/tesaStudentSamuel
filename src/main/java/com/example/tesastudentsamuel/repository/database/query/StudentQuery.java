package com.example.tesastudentsamuel.repository.database.query;

public class StudentQuery {
    public static final String INSERT_STUDENT = """
        INSERT INTO TESA_SAMUEL_Student
          (studentFirstName, studentLastName, studentStateOfOrigin, studentAge, studentStatus)
        VALUES
          (:firstName, :lastName, :stateOfOrigin, :age, COALESCE(:status, 'ACTIVE'))
    """;

    public static final String GET_ALL_STUDENTS = """
        SELECT *
          FROM TESA_SAMUEL_Student
         WHERE studentStatus != 'DELETED'
    """;

    public static final String GET_STUDENT_BY_ID = """
        SELECT *
          FROM TESA_SAMUEL_Student
         WHERE studentId = :studentId
           AND studentStatus != 'DELETED'
    """;

    public static final String GET_STUDENT_BY_FIRSTNAME = """
        SELECT *
          FROM TESA_SAMUEL_Student
         WHERE studentFirstName = :firstName
           AND studentStatus != 'DELETED'
    """;

    public static final String SEARCH_ALL_COLUMNS = """
        SELECT *
          FROM TESA_SAMUEL_Student
          WHERE studentStatus != 'DELETED'
          AND studentFirstName      LIKE :query
          or studentLastName        LIKE :query
          or studentStateOfOrigin   LIKE :query
    """;

    public static final String UPDATE_STUDENT = """
        UPDATE TESA_SAMUEL_Student
           SET studentFirstName     = COALESCE(:firstName,     studentFirstName)
             , studentLastName      = COALESCE(:lastName,      studentLastName)
             , studentStateOfOrigin = COALESCE(:stateOfOrigin, studentStateOfOrigin)
             , studentAge           = COALESCE(:age,           studentAge)
             , studentStatus        = COALESCE(:status,        studentStatus)
        WHERE studentId    = :studentId
           AND studentStatus != 'DELETED'
    """;

    public static final String SOFT_DELETE_STUDENT = """
        UPDATE TESA_SAMUEL_Student
           SET studentStatus = 'DELETED'
         WHERE studentId = :studentId
    """;
}
