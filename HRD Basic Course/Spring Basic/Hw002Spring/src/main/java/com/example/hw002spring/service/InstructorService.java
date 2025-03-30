package com.example.hw002spring.service;

import com.example.hw002spring.dto.request.InstructorRequest;
import com.example.hw002spring.dto.response.PaginatedResponse;
import com.example.hw002spring.mapper.InstructorMapper;
import com.example.hw002spring.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorMapper instructorMapper;

    @Transactional
    public Instructor createInstructor(InstructorRequest request) {
        Instructor instructor = new Instructor();
        instructor.setInstructorName(request.getInstructorName());
        instructor.setEmail(request.getEmail());
        instructorMapper.insert(instructor);
        return instructor;
    }

    public Instructor getInstructorById(Long id) {
        Instructor instructor = instructorMapper.findById(id);
        if (instructor == null) {
            throw new RuntimeException("Instructor not found with id: " + id);
        }
        return instructor;
    }

    public PaginatedResponse<Instructor> getAllInstructors(int page, int size) {
        if (page < 1) page = 1;
        if (size <= 0) size = 10;
        int zeroBasedPage = page - 1;
        int offset = zeroBasedPage * size;
        long totalElements = instructorMapper.countAll();
        if (offset >= totalElements && totalElements > 0) {
            zeroBasedPage = (int) Math.ceil((double) totalElements / size) - 1;
            offset = zeroBasedPage * size;
            page = zeroBasedPage + 1;
        }
        List<Instructor> instructors = instructorMapper.findAllPaginated(offset, size);
        int totalPages = (int) Math.ceil((double) totalElements / size);
        PaginatedResponse<Instructor> response = new PaginatedResponse<>();
        response.setContent(instructors);
        response.setPage(page);
        response.setSize(size);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        return response;
    }

    // Added: Method to update an instructor
    @Transactional
    public Instructor updateInstructor(Long id, InstructorRequest request) {
        Instructor existingInstructor = instructorMapper.findById(id);
        if (existingInstructor == null) {
            throw new RuntimeException("Instructor not found with id: " + id);
        }
        existingInstructor.setInstructorName(request.getInstructorName());
        existingInstructor.setEmail(request.getEmail());
        instructorMapper.update(existingInstructor);
        return existingInstructor;
    }

    // Added: Method to delete an instructor
    @Transactional
    public void deleteInstructor(Long id) {
        int rowsAffected = instructorMapper.deleteById(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("Instructor not found with id: " + id);
        }
    }
}