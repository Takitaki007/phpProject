package com.example.hw002spring.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InstructorRequest {

    @NotBlank(message = "Instructor name is required")
    private String instructorName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email must follow the format: username@domain.com")
    @Schema(description = "Email address of the instructor", example = "jame@gmail.com")
    private String email;
}
