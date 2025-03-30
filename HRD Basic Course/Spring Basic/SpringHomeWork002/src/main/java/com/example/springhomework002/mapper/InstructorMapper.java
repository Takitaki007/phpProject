package com.example.springhomework002.mapper;
import com.example.springhomework002.model.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorMapper {
    @Insert("INSERT INTO instructors (instructorName, email) VALUES (#{instructorName}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "instructorId")
    void insertInstructor(Instructor instructor);

    @Select("SELECT * FROM instructors WHERE instructorId = #{id}")
    Instructor findInstructorById(int id);

    @Update("UPDATE instructors SET instructorName = #{instructorName}, email = #{email} WHERE instructorId = #{instructorId}")
    void updateInstructor(Instructor instructor);

    @Delete("DELETE FROM instructors WHERE instructorId = #{id}")
    void deleteInstructor(int id);

    @Select("SELECT * FROM instructors")
    List<Instructor> findAllInstructors();
}