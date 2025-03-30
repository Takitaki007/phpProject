package com.example.springhomework002.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Instructor {
    private int instructorId;
    private String instructorName;
    private String email;
}

