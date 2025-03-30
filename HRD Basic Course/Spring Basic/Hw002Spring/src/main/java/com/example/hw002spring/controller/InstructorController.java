package com.example.hw002spring.controller;

import com.example.hw002spring.dto.request.InstructorRequest;
import com.example.hw002spring.dto.response.APIResponse;
import com.example.hw002spring.dto.response.PaginatedResponse;
import com.example.hw002spring.model.Instructor;
import com.example.hw002spring.service.InstructorService;
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
@RequestMapping("/api/v1/instructors")
@Validated
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping
    @Operation(summary = "Create a new instructor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<APIResponse<Instructor>> createInstructor(@Valid @RequestBody InstructorRequest request) {
        Instructor instructor = instructorService.createInstructor(request);
        APIResponse<Instructor> response = new APIResponse<>("Instructor created successfully", instructor, "CREATED");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all instructors with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructors retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    public ResponseEntity<APIResponse<PaginatedResponse<Instructor>>> getAllInstructors(
            @Parameter(description = "Page number (1-based, e.g., 1 for the first page)", example = "1")
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "Page number must be 1 or greater") int page,

            @Parameter(description = "Number of items per page", example = "2")
            @RequestParam(defaultValue = "2") @Min(value = 1, message = "Page size must be at least 1") int size
    ) {
        PaginatedResponse<Instructor> paginatedInstructors = instructorService.getAllInstructors(page, size);
        String message = paginatedInstructors.getContent().isEmpty() ? "No instructors found" : "Instructors retrieved successfully";
        APIResponse<PaginatedResponse<Instructor>> response = new APIResponse<>(message, paginatedInstructors, "OK");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get instructor by ID", description = "Retrieves a specific instructor by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    public ResponseEntity<APIResponse<Instructor>> getInstructorById(
            @Parameter(description = "ID of the instructor to retrieve", example = "1")
            @PathVariable @Min(value = 1, message = "Instructor ID must be greater than 0") Long id
    ) {
        Instructor instructor = instructorService.getInstructorById(id);
        APIResponse<Instructor> response = new APIResponse<>("Instructor retrieved successfully", instructor, "100 CONTINUE");
        return ResponseEntity.ok(response);
    }

    // Added: PUT endpoint to update an instructor
    @PutMapping("/{id}")
    @Operation(summary = "Update an instructor", description = "Updates an existing instructor by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    public ResponseEntity<APIResponse<Instructor>> updateInstructor(
            @Parameter(description = "ID of the instructor to update", example = "1")
            @PathVariable @Min(value = 1, message = "Instructor ID must be greater than 0") Long id,
            @Valid @RequestBody InstructorRequest request
    ) {
        Instructor updatedInstructor = instructorService.updateInstructor(id, request);
        APIResponse<Instructor> response = new APIResponse<>("Instructor updated successfully", updatedInstructor, "OK");
        return ResponseEntity.ok(response);
    }

    // Added: DELETE endpoint to delete an instructor
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an instructor", description = "Deletes an instructor by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    public ResponseEntity<APIResponse<String>> deleteInstructor(
            @Parameter(description = "ID of the instructor to delete", example = "1")
            @PathVariable @Min(value = 1, message = "Instructor ID must be greater than 0") Long id
    ) {
        instructorService.deleteInstructor(id);
        APIResponse<String> response = new APIResponse<>("Instructor deleted successfully", null, "OK");
        return ResponseEntity.ok(response);
    }
}