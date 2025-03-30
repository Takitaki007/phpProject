package com.example.springhomework002.mapper;
import com.example.springhomework002.model.Course;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Insert("INSERT INTO course (courseName, description, instructorId) VALUES (#{courseName}, #{description}, #{instructor.instructorId})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId")
    void insertCourse(Course course);

    @Select("SELECT * FROM course WHERE courseId = #{id}")
    Course findCourseById(int id);

    @Update("UPDATE course SET courseName = #{courseName}, description = #{description}, instructorId = #{instructor.instructorId} WHERE courseId = #{courseId}")
    void updateCourse(Course course);

    @Delete("DELETE FROM course WHERE courseId = #{id}")
    void deleteCourse(int id);

    @Select("SELECT * FROM course")
    List<Course> findAllCourses();
}