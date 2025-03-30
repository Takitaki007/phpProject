package com.example.api2homework.repository;


import com.example.api2homework.model.entity.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepo {

    @Insert("INSERT INTO instructors (instructor_name, email) VALUES (#{instructorName}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "instructorId")
    void insertInstructor(Instructor instructor);

    @Select("SELECT * FROM instructors WHERE instructor_id = #{id}")
    Instructor findInstructorById(Integer id);

    @Update("UPDATE instructors SET instructor_name = #{instructorName}, email = #{email} WHERE instructor_id = #{instructorId}")
    void updateInstructor(Instructor instructor);

    @Delete("DELETE FROM instructors WHERE instructor_id = #{id}")
    void deleteInstructor(Integer id);

    @Select("SELECT * FROM instructors")
    List<Instructor> findAllInstructors();
}