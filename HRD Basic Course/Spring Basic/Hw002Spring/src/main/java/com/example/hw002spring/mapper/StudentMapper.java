package com.example.hw002spring.mapper;

import com.example.hw002spring.model.Course;
import com.example.hw002spring.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO students (student_name, email, phone_number) VALUES (#{studentName}, #{email}, #{phoneNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "studentId")
    void insert(Student student);

    @Select("SELECT * FROM students WHERE student_id = #{id}")
    @Results(id = "studentResultMap", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id", many = @Many(select = "findCoursesByStudentId"))
    })
    Student findById(Long id);

    @Select("SELECT c.* FROM courses c JOIN student_course sc ON c.course_id = sc.course_id WHERE sc.student_id = #{studentId}")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructor", column = "instructor_id", one = @One(select = "com.example.hw002spring.mapper.InstructorMapper.findById"))
    })
    List<Course> findCoursesByStudentId(Long studentId);

    @Select("SELECT * FROM students ORDER BY student_id LIMIT #{size} OFFSET #{offset}")
    @ResultMap("studentResultMap")
    List<Student> findAllPaginated(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM students")
    long countAll();

    @Update("UPDATE students SET student_name = #{studentName}, email = #{email}, phone_number = #{phoneNumber} WHERE student_id = #{studentId}")
    void update(Student student);

    @Delete("DELETE FROM students WHERE student_id = #{id}")
    int deleteById(Long id);
}