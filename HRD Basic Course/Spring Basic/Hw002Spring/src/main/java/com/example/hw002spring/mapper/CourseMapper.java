package com.example.hw002spring.mapper;

import com.example.hw002spring.model.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Insert("INSERT INTO courses (course_name, description, instructor_id) VALUES (#{courseName}, #{description}, #{instructorId})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId")
    void insert(Course course);

    @Select("SELECT * FROM courses WHERE course_id = #{id}")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructorId", column = "instructor_id")
    })
    Course findById(Long id);

    @Select("SELECT * FROM courses ORDER BY course_id LIMIT #{size} OFFSET #{offset}")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructorId", column = "instructor_id")
    })
    List<Course> findAllPaginated(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM courses")
    long countAll();

    @Update("UPDATE courses SET course_name = #{courseName}, description = #{description}, instructor_id = #{instructorId} WHERE course_id = #{courseId}")
    void update(Course course);

    @Delete("DELETE FROM courses WHERE course_id = #{id}")
    int deleteById(Long id);
}