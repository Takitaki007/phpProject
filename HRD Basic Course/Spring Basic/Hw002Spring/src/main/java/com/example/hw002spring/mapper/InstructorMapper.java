package com.example.hw002spring.mapper;

import com.example.hw002spring.model.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorMapper {

    @Insert("INSERT INTO instructors (instructor_name, email) VALUES (#{instructorName}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "instructorId")
    void insert(Instructor instructor);

    @Select("SELECT * FROM instructors WHERE instructor_id = #{id}")
    @Results({
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email", column = "email")
    })
    Instructor findById(Long id);

    @Select("SELECT * FROM instructors ORDER BY instructor_id LIMIT #{size} OFFSET #{offset}")
    @Results({
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email", column = "email")
    })
    List<Instructor> findAllPaginated(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM instructors")
    long countAll();


    @Update("UPDATE instructors SET instructor_name = #{instructorName}, email = #{email} WHERE instructor_id = #{instructorId}")
    void update(Instructor instructor);


    @Delete("DELETE FROM instructors WHERE instructor_id = #{id}")
    int deleteById(Long id);
}