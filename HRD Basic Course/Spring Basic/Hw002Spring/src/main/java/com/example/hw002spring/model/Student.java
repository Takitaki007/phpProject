package com.example.hw002spring.model;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private Long studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<Course> courses; // Changed from List<Long> coursesId to List<Course> courses
}