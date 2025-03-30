package com.example.hw002spring.model;

import lombok.Data;

@Data
public class Course {
    private Long courseId;
    private String courseName;
    private String description;
    private Long instructorId; // Keep this for database mapping
    private Instructor instructor; // To hold the full Instructor object
}