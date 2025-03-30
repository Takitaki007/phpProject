package com.example.springhomework002.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private int studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<Course> courses;
}
