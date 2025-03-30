package com.example.hw002spring.service;

import com.example.hw002spring.dto.request.CourseRequest;
import com.example.hw002spring.dto.response.PaginatedResponse;
import com.example.hw002spring.mapper.CourseMapper;
import com.example.hw002spring.mapper.InstructorMapper;
import com.example.hw002spring.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private InstructorMapper instructorMapper;

    @Transactional
    public Course createCourse(CourseRequest request) {
        // Validate that the instructor exists
        if (instructorMapper.findById(request.getInstructorId()) == null) {
            throw new RuntimeException("Instructor not found with id: " + request.getInstructorId());
        }

        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setInstructorId(request.getInstructorId());
        courseMapper.insert(course);

        // Fetch the course with the instructor details
        return courseMapper.findById(course.getCourseId());
    }

    public PaginatedResponse<Course> getAllCourses(int page, int size) {
        if (page < 1) page = 1;
        if (size <= 0) size = 10;
        int zeroBasedPage = page - 1;
        int offset = zeroBasedPage * size;
        long totalElements = courseMapper.countAll();
        if (offset >= totalElements && totalElements > 0) {
            zeroBasedPage = (int) Math.ceil((double) totalElements / size) - 1;
            offset = zeroBasedPage * size;
            page = zeroBasedPage + 1;
        }
        List<Course> courses = courseMapper.findAllPaginated(offset, size);
        int totalPages = (int) Math.ceil((double) totalElements / size);
        PaginatedResponse<Course> response = new PaginatedResponse<>();
        response.setContent(courses);
        response.setPage(page);
        response.setSize(size);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        return response;
    }

    public Course getCourseById(Long id) {
        Course course = courseMapper.findById(id);
        if (course == null) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        return course;
    }

    @Transactional
    public Course updateCourse(Long id, CourseRequest request) {
        // Validate that the course exists
        Course existingCourse = courseMapper.findById(id);
        if (existingCourse == null) {
            throw new RuntimeException("Course not found with id: " + id);
        }

        // Validate that the instructor exists
        if (instructorMapper.findById(request.getInstructorId()) == null) {
            throw new RuntimeException("Instructor not found with id: " + request.getInstructorId());
        }

        existingCourse.setCourseName(request.getCourseName());
        existingCourse.setDescription(request.getDescription());
        existingCourse.setInstructorId(request.getInstructorId());
        courseMapper.update(existingCourse);

        // Fetch the updated course with the instructor details
        return courseMapper.findById(id);
    }

    @Transactional
    public void deleteCourse(Long id) {
        int rowsAffected = courseMapper.deleteById(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("Course not found with id: " + id);
        }
    }
}