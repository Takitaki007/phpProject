package com.example.hw002spring.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentCourse {
    private Long studentId;
    private Long courseId;
    private LocalDateTime enrollmentDate;
}