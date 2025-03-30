package com.example.springhomework002.service;

import com.example.springhomework002.mapper.InstructorMapper;
import com.example.springhomework002.model.Instructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstructorService {
    private final InstructorMapper instructorMapper;

    public InstructorService(InstructorMapper instructorMapper) {
        this.instructorMapper = instructorMapper;
    }

    public void addInstructor(Instructor instructor) {
        instructorMapper.insertInstructor(instructor);
    }

    public Instructor getInstructorById(int id) {
        return instructorMapper.findInstructorById(id);
    }

    public void updateInstructor(Instructor instructor) {
        instructorMapper.updateInstructor(instructor);
    }

    public void deleteInstructor(int id) {
        instructorMapper.deleteInstructor(id);
    }

    public List<Instructor> getAllInstructors() {
        return instructorMapper.findAllInstructors();
    }
}