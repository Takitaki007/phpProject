package com.example.api2homework.service.impl;

import com.example.api2homework.mapper.InstructorMapper;
import com.example.api2homework.model.dto.request.InstructorRequest;
import com.example.api2homework.model.dto.response.InstructorDto;
import com.example.api2homework.model.entity.Instructor;
import com.example.api2homework.repository.InstructorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepo instructorRepo;
    private final InstructorMapper instructorMapper;

    public InstructorServiceImpl(InstructorRepo instructorRepo, InstructorMapper instructorMapper) {
        this.instructorRepo = instructorRepo;
        this.instructorMapper = instructorMapper;
    }

    @Override
    public InstructorDto createInstructor(InstructorRequest request) {
        Instructor instructor = instructorMapper.toEntity(request);
        instructorRepo.insertInstructor(instructor);
        return instructorMapper.toResponse(instructor);
    }

    @Override
    public InstructorDto getInstructorById(Integer id) {
        Instructor instructor = instructorRepo.findInstructorById(id);
        return instructorMapper.toResponse(instructor);
    }

    @Override
    public InstructorDto updateInstructor(Integer id, InstructorRequest request) {
        Instructor instructor = instructorRepo.findInstructorById(id);
        instructor.setInstructorName(request.getInstructorName());
        instructor.setEmail(request.getEmail());
        instructorRepo.updateInstructor(instructor);
        return instructorMapper.toResponse(instructor);
    }

    @Override
    public void deleteInstructor(Integer id) {
        instructorRepo.deleteInstructor(id);
    }

    @Override
    public List<InstructorDto> getAllInstructors() {
        return instructorRepo.findAllInstructors().stream()
                .map(instructorMapper::toResponse)
                .collect(Collectors.toList());
    }
}