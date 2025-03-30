package com.example.api2homework.mapper;

import com.example.api2homework.model.dto.request.InstructorRequest;
import com.example.api2homework.model.dto.response.InstructorDto;
import com.example.api2homework.model.entity.Instructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InstructorMapper {
//    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);

    Instructor toEntity(InstructorRequest request);
    InstructorDto toResponse(Instructor instructor);
}