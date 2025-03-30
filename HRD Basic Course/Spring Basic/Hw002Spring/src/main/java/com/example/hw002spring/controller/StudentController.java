package com.example.hw002spring.controller;

import com.example.hw002spring.dto.request.StudentRequest;
import com.example.hw002spring.dto.response.APIResponse;
import com.example.hw002spring.dto.response.PaginatedResponse;
import com.example.hw002spring.model.Student;
import com.example.hw002spring.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Create a new student", description = "Adds a new student to the system and enrolls them in the specified courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    public ResponseEntity<APIResponse<Student>> createStudent(@Valid @RequestBody StudentRequest request) {
        Student student = studentService.createStudent(request);
        APIResponse<Student> response = new APIResponse<>("The student has been successfully added.", student, "CREATED");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all students with pagination", description = "Retrieves a paginated list of all students in the system. Page numbering is 1-based (page=1 is the first page).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    public ResponseEntity<APIResponse<PaginatedResponse<Student>>> getAllStudents(
            @Parameter(description = "Page number (1-based, e.g., 1 for the first page)", example = "1")
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "Page number must be 1 or greater") int page,

            @Parameter(description = "Number of items per page", example = "2")
            @RequestParam(defaultValue = "2") @Min(value = 1, message = "Page size must be at least 1") int size
    ) {
        PaginatedResponse<Student> paginatedStudents = studentService.getAllStudents(page, size);
        String message = paginatedStudents.getContent().isEmpty() ? "No students found" : "Students retrieved successfully";
        APIResponse<PaginatedResponse<Student>> response = new APIResponse<>(message, paginatedStudents, "OK");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID", description = "Retrieves a specific student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<APIResponse<Student>> getStudentById(
            @Parameter(description = "ID of the student to retrieve", example = "1")
            @PathVariable @Min(value = 1, message = "Student ID must be greater than 0") Long id
    ) {
        Student student = studentService.getStudentById(id);
        APIResponse<Student> response = new APIResponse<>("Student retrieved successfully", student, "OK");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a student", description = "Updates an existing student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Student or Course not found")
    })
    public ResponseEntity<APIResponse<Student>> updateStudent(
            @Parameter(description = "ID of the student to update", example = "1")
            @PathVariable @Min(value = 1, message = "Student ID must be greater than 0") Long id,
            @Valid @RequestBody StudentRequest request
    ) {
        Student updatedStudent = studentService.updateStudent(id, request);
        APIResponse<Student> response = new APIResponse<>("The student has been successfully updated.", updatedStudent, "OK");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a student", description = "Deletes a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<APIResponse<String>> deleteStudent(
            @Parameter(description = "ID of the student to delete", example = "1")
            @PathVariable @Min(value = 1, message = "Student ID must be greater than 0") Long id
    ) {
        studentService.deleteStudent(id);
        APIResponse<String> response = new APIResponse<>("Student deleted successfully", null, "OK");
        return ResponseEntity.ok(response);
    }
}