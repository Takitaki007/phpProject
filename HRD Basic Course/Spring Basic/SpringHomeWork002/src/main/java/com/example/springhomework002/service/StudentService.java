package com.example.springhomework002.service;
import com.example.springhomework002.mapper.StudentMapper;
import com.example.springhomework002.model.Student;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public void addStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    public Student getStudentById(int id) {
        return studentMapper.findStudentById(id);
    }

    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    public void deleteStudent(int id) {
        studentMapper.deleteStudent(id);
    }

    public List<Student> getAllStudents() {
        return studentMapper.findAllStudents();
    }
}
