package com.example.api2homework.controller;


import com.example.api2homework.model.dto.request.InstructorRequest;
import com.example.api2homework.model.dto.response.ApiResponse;
import com.example.api2homework.model.dto.response.InstructorDto;
import com.example.api2homework.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

//    @PostMapping
//    public ResponseEntity<ApiResponse<InstructorDto>> createInstructor(@RequestBody InstructorRequest request) {
//        InstructorDto response = instructorService.createInstructor(request);
//        ApiResponse<InstructorDto> apiResponse = ApiResponse.<InstructorDto>builder()
//                .message("Instructor created successfully")
//                .status(HttpStatus.CREATED)
//                .payload(response)
//                .build();
//        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse<InstructorDto>> getInstructorById(@PathVariable Integer id) {
//        InstructorDto response = instructorService.getInstructorById(id);
//        ApiResponse<InstructorDto> apiResponse = ApiResponse.<InstructorDto>builder()
//                .message("Instructor retrieved successfully")
//                .status(HttpStatus.OK)
//                .payload(response)
//                .build();
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse<InstructorDto>> updateInstructor(@PathVariable Integer id, @RequestBody InstructorRequest request) {
//        InstructorDto response = instructorService.updateInstructor(id, request);
//        ApiResponse<InstructorDto> apiResponse = ApiResponse.<InstructorDto>builder()
//                .message("Instructor updated successfully")
//                .status(HttpStatus.OK)
//                .payload(response)
//                .build();
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse<Void>> deleteInstructor(@PathVariable Integer id) {
//        instructorService.deleteInstructor(id);
//        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
//                .message("Instructor deleted successfully")
//                .status(HttpStatus.OK)
//                .build();
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @GetMapping
//    public ResponseEntity<ApiResponse<List<InstructorDto>>> getAllInstructors() {
//        List<InstructorDto> response = instructorService.getAllInstructors();
//        ApiResponse<List<InstructorDto>> apiResponse = ApiResponse.<List<InstructorDto>>builder()
//                .message("Instructors retrieved successfully")
//                .status(HttpStatus.OK)
//                .payload(response)
//                .build();
//        return ResponseEntity.ok(apiResponse);
//    }
}