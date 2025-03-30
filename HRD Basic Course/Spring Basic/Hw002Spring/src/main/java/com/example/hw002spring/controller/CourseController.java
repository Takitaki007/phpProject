package com.example.hw002spring.controller;

import com.example.hw002spring.dto.request.CourseRequest;
import com.example.hw002spring.dto.response.APIResponse;
import com.example.hw002spring.dto.response.PaginatedResponse;
import com.example.hw002spring.model.Course;
import com.example.hw002spring.service.CourseService;
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
@RequestMapping("/api/v1/courses")
@Validated
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    @Operation(summary = "Create a new course", description = "Adds a new course to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    public ResponseEntity<APIResponse<Course>> createCourse(@Valid @RequestBody CourseRequest request) {
        Course course = courseService.createCourse(request);
        APIResponse<Course> response = new APIResponse<>("The course has been successfully added.", course, "CREATED");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all courses with pagination", description = "Retrieves a paginated list of all courses in the system. Page numbering is 1-based (page=1 is the first page).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    public ResponseEntity<APIResponse<PaginatedResponse<Course>>> getAllCourses(
            @Parameter(description = "Page number (1-based, e.g., 1 for the first page)", example = "1")
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "Page number must be 1 or greater") int page,

            @Parameter(description = "Number of items per page", example = "2")
            @RequestParam(defaultValue = "2") @Min(value = 1, message = "Page size must be at least 1") int size
    ) {
        PaginatedResponse<Course> paginatedCourses = courseService.getAllCourses(page, size);
        String message = paginatedCourses.getContent().isEmpty() ? "No courses found" : "Courses retrieved successfully";
        APIResponse<PaginatedResponse<Course>> response = new APIResponse<>(message, paginatedCourses, "OK");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID", description = "Retrieves a specific course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    public ResponseEntity<APIResponse<Course>> getCourseById(
            @Parameter(description = "ID of the course to retrieve", example = "1")
            @PathVariable @Min(value = 1, message = "Course ID must be greater than 0") Long id
    ) {
        Course course = courseService.getCourseById(id);
        APIResponse<Course> response = new APIResponse<>("Course retrieved successfully", course, "OK");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a course", description = "Updates an existing course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Course or Instructor not found")
    })
    public ResponseEntity<APIResponse<Course>> updateCourse(
            @Parameter(description = "ID of the course to update", example = "1")
            @PathVariable @Min(value = 1, message = "Course ID must be greater than 0") Long id,
            @Valid @RequestBody CourseRequest request
    ) {
        Course updatedCourse = courseService.updateCourse(id, request);
        APIResponse<Course> response = new APIResponse<>("The course has been successfully updated.", updatedCourse, "OK");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a course", description = "Deletes a course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    public ResponseEntity<APIResponse<String>> deleteCourse(
            @Parameter(description = "ID of the course to delete", example = "1")
            @PathVariable @Min(value = 1, message = "Course ID must be greater than 0") Long id
    ) {
        courseService.deleteCourse(id);
        APIResponse<String> response = new APIResponse<>("Course deleted successfully", null, "OK");
        return ResponseEntity.ok(response);
    }
}