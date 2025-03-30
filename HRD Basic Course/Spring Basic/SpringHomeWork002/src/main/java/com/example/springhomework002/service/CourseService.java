package com.example.springhomework002.service;

import com.example.springhomework002.mapper.CourseMapper;
import com.example.springhomework002.model.Course;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
    private final CourseMapper courseMapper;

    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public void addCourse(Course course) {
        courseMapper.insertCourse(course);
    }

    public Course getCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    public void updateCourse(Course course) {
        courseMapper.updateCourse(course);
    }

    public void deleteCourse(int id) {
        courseMapper.deleteCourse(id);
    }

    public List<Course> getAllCourses() {
        return courseMapper.findAllCourses();
    }
}
