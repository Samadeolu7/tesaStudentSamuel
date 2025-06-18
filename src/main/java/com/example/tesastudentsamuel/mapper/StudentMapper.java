package com.example.tesastudentsamuel.mapper;

import com.example.tesastudentsamuel.model.entity.Student;
import com.example.tesastudentsamuel.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    // OPTIONAL: you can omit the INSTANCE field when using componentModel="spring"
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    StudentResponse toResponse(Student student);
}
