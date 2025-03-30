package com.example.hw002spring.service;

import com.example.hw002spring.dto.request.StudentRequest;
import com.example.hw002spring.dto.response.APIResponse;
import com.example.hw002spring.dto.response.PaginatedResponse;
import com.example.hw002spring.mapper.CourseMapper;
import com.example.hw002spring.mapper.StudentCourseMapper;
import com.example.hw002spring.mapper.StudentMapper;
import com.example.hw002spring.model.Student;
import com.example.hw002spring.model.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Transactional
    public Student createStudent(StudentRequest request) {
        // Create the student
        Student student = new Student();
        student.setStudentName(request.getStudentName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        studentMapper.insert(student);

        // Enroll the student in the specified courses
        if (request.getCoursesId() != null && !request.getCoursesId().isEmpty()) {
            for (Long courseId : request.getCoursesId()) {
                // Validate that the course exists
                if (courseMapper.findById(courseId) == null) {
                    throw new RuntimeException("Course not found with id: " + courseId);
                }
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudentId(student.getStudentId());
                studentCourse.setCourseId(courseId);
                studentCourse.setEnrollmentDate(LocalDateTime.now());
                studentCourseMapper.insert(studentCourse);
            }
        }

        // Fetch the student with their courses
        return studentMapper.findById(student.getStudentId());
    }

    public PaginatedResponse<Student> getAllStudents(int page, int size) {
        if (page < 1) page = 1;
        if (size <= 0) size = 10;
        int zeroBasedPage = page - 1;
        int offset = zeroBasedPage * size;
        long totalElements = studentMapper.countAll();
        if (offset >= totalElements && totalElements > 0) {
            zeroBasedPage = (int) Math.ceil((double) totalElements / size) - 1;
            offset = zeroBasedPage * size;
            page = zeroBasedPage + 1;
        }
        List<Student> students = studentMapper.findAllPaginated(offset, size);
        int totalPages = (int) Math.ceil((double) totalElements / size);
        PaginatedResponse<Student> response = new PaginatedResponse<>();
        response.setContent(students);
        response.setPage(page);
        response.setSize(size);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        return response;
    }

    public Student getStudentById(Long id) {
        Student student = studentMapper.findById(id);
        if (student == null) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        return student;
    }

    @Transactional
    public Student updateStudent(Long id, StudentRequest request) {
        Student existingStudent = studentMapper.findById(id);
        if (existingStudent == null) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        existingStudent.setStudentName(request.getStudentName());
        existingStudent.setEmail(request.getEmail());
        existingStudent.setPhoneNumber(request.getPhoneNumber());
        studentMapper.update(existingStudent);

        // Update enrollments if coursesId is provided
        if (request.getCoursesId() != null) {
            // Delete existing enrollments
            studentCourseMapper.deleteByStudentId(id);
            // Add new enrollments
            for (Long courseId : request.getCoursesId()) {
                if (courseMapper.findById(courseId) == null) {
                    throw new RuntimeException("Course not found with id: " + courseId);
                }
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudentId(id);
                studentCourse.setCourseId(courseId);
                studentCourse.setEnrollmentDate(LocalDateTime.now());
                studentCourseMapper.insert(studentCourse);
            }
        }

        return studentMapper.findById(id);
    }

    @Transactional
    public void deleteStudent(Long id) {
        int rowsAffected = studentMapper.deleteById(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        // Note: Enrollments are automatically deleted due to ON DELETE CASCADE
    }
}