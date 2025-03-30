package com.example.springhomework002.mapper;
import com.example.springhomework002.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Insert("INSERT INTO student (studentName, email, phoneNumber) VALUES (#{studentName}, #{email}, #{phoneNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "studentId")
    void insertStudent(Student student);

    @Select("SELECT * FROM student WHERE studentId = #{id}")
    Student findStudentById(int id);

    @Update("UPDATE student SET studentName = #{studentName}, email = #{email}, phoneNumber = #{phoneNumber} WHERE studentId = #{studentId}")
    void updateStudent(Student student);

    @Delete("DELETE FROM student WHERE studentId = #{id}")
    void deleteStudent(int id);

    @Select("SELECT * FROM student")
    List<Student> findAllStudents();
}