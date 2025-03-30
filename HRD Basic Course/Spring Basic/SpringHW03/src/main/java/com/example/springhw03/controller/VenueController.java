package com.example.springhw03.controller;


import com.example.springhw03.dto.request.VenueRequest;
import com.example.springhw03.dto.response.APIResponse;
import com.example.springhw03.dto.response.ErrorResponse;
import com.example.springhw03.dto.response.PaginatedResponse;
import com.example.springhw03.model.Venue;
import com.example.springhw03.service.VenueService;
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
@RequestMapping("/api/v1/venues")
@Validated
public class VenueController {

    @Autowired
    private VenueService venueService;

    @PostMapping
    @Operation(summary = "Create a new venue", description = "Adds a new venue to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<APIResponse<Venue>> createVenue(@Valid @RequestBody VenueRequest request) {
        try {
            Venue venue = venueService.createVenue(request);
            APIResponse<Venue> response = new APIResponse<>("The venue has been successfully added.", venue, "CREATED");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create venue: " + e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Get all venues with pagination", description = "Retrieves a paginated list of all venues")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venues retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    public ResponseEntity<APIResponse<PaginatedResponse<Venue>>> getAllVenues(
            @Parameter(description = "Offset for pagination", example = "0")
            @RequestParam(defaultValue = "0") @Min(value = 1, message = "Offset must be greater than 0") int offset,

            @Parameter(description = "Limit for pagination", example = "10")
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Limit must be greater than 0") int limit
    ) {
        try {
            PaginatedResponse<Venue> paginatedVenues = venueService.getAllVenues(offset, limit);
            String message = paginatedVenues.getContent().isEmpty() ? "No venues found" : "Venues retrieved successfully";
            APIResponse<PaginatedResponse<Venue>> response = new APIResponse<>(message, paginatedVenues, "OK");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve venues: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get venue by ID", description = "Retrieves a specific venue by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "404", description = "Venue not found")
    })
    public ResponseEntity<APIResponse<Venue>> getVenueById(
            @Parameter(description = "ID of the venue to retrieve", example = "1")
            @PathVariable("id") @Min(value = 1, message = "Venue ID must be greater than 0") Long id
    ) {
        try {
            Venue venue = venueService.getVenueById(id);
            APIResponse<Venue> response = new APIResponse<>("The venue has been successfully retrieved.", venue, "OK");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a venue", description = "Updates an existing venue by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Venue not found")
    })
    public ResponseEntity<APIResponse<Venue>> updateVenue(
            @Parameter(description = "ID of the venue to update", example = "1")
            @PathVariable("id") @Min(value = 1, message = "Venue ID must be greater than 0") Long id,
            @Valid @RequestBody VenueRequest request
    ) {
        try {
            Venue updatedVenue = venueService.updateVenue(id, request);
            APIResponse<Venue> response = new APIResponse<>("The venue has been successfully updated.", updatedVenue, "100 CONTINUE");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a venue", description = "Deletes a venue by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "404", description = "Venue not found")
    })
    public ResponseEntity<APIResponse<String>> deleteVenue(
            @Parameter(description = "ID of the venue to delete", example = "1")
            @PathVariable("id") @Min(value = 1, message = "Venue ID must be greater than 0") Long id
    ) {
        try {
            venueService.deleteVenue(id);
            APIResponse<String> response = new APIResponse<>("The venue has been successfully deleted.", null, "OK");
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