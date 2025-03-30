package com.example.api2homework.service;

import com.example.api2homework.model.dto.request.InstructorRequest;
import com.example.api2homework.model.dto.response.InstructorDto;

import java.util.List;
public interface InstructorService {
    InstructorDto createInstructor(InstructorRequest request);
    InstructorDto getInstructorById(Integer id);
    InstructorDto updateInstructor(Integer id, InstructorRequest request);
    void deleteInstructor(Integer id);
    List<InstructorDto> getAllInstructors();
}