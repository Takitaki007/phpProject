package com.example.hw002spring.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseRequest {
    @NotBlank(message = "Course name is required")
    private String courseName;

    private String description;

    @NotNull(message = "Instructor ID is required") // Changed from @NotBlank to @NotNull
    @Min(value = 1, message = "Instructor ID must be greater than 0")
    @Max(value = 999999, message = "Instructor ID must be less than or equal to 999999")
    private Long instructorId;
}