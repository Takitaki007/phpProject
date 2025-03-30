package com.example.hw002spring.mapper;

import com.example.hw002spring.model.StudentCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentCourseMapper {

    @Insert("INSERT INTO student_course (student_id, course_id, enrollment_date) VALUES (#{studentId}, #{courseId}, #{enrollmentDate})")
    void insert(StudentCourse studentCourse);

    @Select("SELECT course_id FROM student_course WHERE student_id = #{studentId}")
    List<Long> findCourseIdsByStudentId(Long studentId);

    @Delete("DELETE FROM student_course WHERE student_id = #{studentId}")
    void deleteByStudentId(Long studentId);
}