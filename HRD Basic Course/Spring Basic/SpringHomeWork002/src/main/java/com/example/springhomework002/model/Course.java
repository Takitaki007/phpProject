package com.example.springhomework002.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    private int courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}
