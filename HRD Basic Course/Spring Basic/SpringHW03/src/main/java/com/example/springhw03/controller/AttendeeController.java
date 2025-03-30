package com.example.springhw03.controller;

import com.example.springhw03.dto.request.AttendeeRequest;
import com.example.springhw03.dto.response.APIResponse;
import com.example.springhw03.dto.response.ErrorResponse;
import com.example.springhw03.dto.response.PaginatedResponse;
import com.example.springhw03.model.Attendee;
import com.example.springhw03.service.AttendeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendees")
@Validated
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @PostMapping
    @Operation(summary = "Create a new attendee", description = "Adds a new attendee to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attendee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<APIResponse<Attendee>> createAttendee(@Valid @RequestBody AttendeeRequest request) {
        try {
            Attendee attendee = attendeeService.createAttendee(request);
            APIResponse<Attendee> response = new APIResponse<>("The attendee has been successfully added.", attendee, "CREATED");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create attendee: " + e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Get all attendees with pagination", description = "Retrieves a paginated list of all attendees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attendees retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    public ResponseEntity<APIResponse<PaginatedResponse<Attendee>>> getAllAttendees(
            @Parameter(description = "Offset for pagination", example = "0")
            @RequestParam(defaultValue = "0") @Min(value = 1, message = "Offset must be greater than 0") int offset,

            @Parameter(description = "Limit for pagination", example = "10")
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Limit must be greater than 0") int limit
    ) {
        try {
            PaginatedResponse<Attendee> paginatedAttendees = attendeeService.getAllAttendees(offset, limit);
            String message = paginatedAttendees.getContent().isEmpty() ? "No attendees found" : "Attendees retrieved successfully";
            APIResponse<PaginatedResponse<Attendee>> response = new APIResponse<>(message, paginatedAttendees, "OK");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve attendees: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get attendee by ID", description = "Retrieves a specific attendee by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attendee retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "404", description = "Attendee not found")
    })
    public ResponseEntity<APIResponse<Attendee>> getAttendeeById(
            @Parameter(description = "ID of the attendee to retrieve", example = "1")
            @PathVariable("id") @Min(value = 1, message = "Attendee ID must be greater than 0") Long id
    ) {
        try {
            Attendee attendee = attendeeService.getAttendeeById(id);
            APIResponse<Attendee> response = new APIResponse<>("The attendee has been successfully retrieved.", attendee, "OK");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an attendee", description = "Updates an existing attendee by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attendee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Attendee or Event not found")
    })
    public ResponseEntity<APIResponse<Attendee>> updateAttendee(
            @Parameter(description = "ID of the attendee to update", example = "1")
            @PathVariable("id") @Min(value = 1, message = "Attendee ID must be greater than 0") Long id,
            @Valid @RequestBody AttendeeRequest request
    ) {
        try {
            Attendee updatedAttendee = attendeeService.updateAttendee(id, request);
            APIResponse<Attendee> response = new APIResponse<>("The attendee has been successfully updated.", updatedAttendee, "100 CONTINUE");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an attendee", description = "Deletes an attendee by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attendee deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "404", description = "Attendee not found")
    })
    public ResponseEntity<APIResponse<String>> deleteAttendee(
            @Parameter(description = "ID of the attendee to delete", example = "1")
            @PathVariable("id") @Min(value = 1, message = "Attendee ID must be greater than 0") Long id
    ) {
        try {
            attendeeService.deleteAttendee(id);
            APIResponse<String> response = new APIResponse<>("The attendee has been successfully deleted.", null, "OK");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTitle("Not Found");
        errorResponse.setStatus(404);
        errorResponse.setDetail(ex.getMessage());
        errorResponse.setInstance(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}