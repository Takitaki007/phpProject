package com.example.springhw03.controller;

import com.example.springhw03.dto.request.EventRequest;
import com.example.springhw03.dto.response.APIResponse;
import com.example.springhw03.dto.response.ErrorResponse;
import com.example.springhw03.dto.response.PaginatedResponse;
import com.example.springhw03.model.Event;
import com.example.springhw03.service.EventService;
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
@RequestMapping("/api/v1/events")
@Validated
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    @Operation(summary = "Create a new event", description = "Adds a new event to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Venue or Attendee not found")
    })
    public ResponseEntity<APIResponse<Event>> createEvent(@Valid @RequestBody EventRequest request) {
        try {
            Event event = eventService.createEvent(request);
            APIResponse<Event> response = new APIResponse<>("The event has been successfully added.", event, "OK");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create event: " + e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Get all events with pagination", description = "Retrieves a paginated list of all events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    public ResponseEntity<APIResponse<PaginatedResponse<Event>>> getAllEvents(
            @Parameter(description = "Offset for pagination", example = "0")
            @RequestParam(defaultValue = "0") @Min(value = 1, message = "Offset must be greater than 0") int offset,

            @Parameter(description = "Limit for pagination", example = "10")
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Limit must be greater than 0") int limit
    ) {
        try {
            PaginatedResponse<Event> paginatedEvents = eventService.getAllEvents(offset, limit);
            String message = paginatedEvents.getContent().isEmpty() ? "No events found" : "Events retrieved successfully";
            APIResponse<PaginatedResponse<Event>> response = new APIResponse<>(message, paginatedEvents, "100 CONTINUE");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve events: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID", description = "Retrieves a specific event by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<APIResponse<Event>> getEventById(
            @Parameter(description = "ID of the event to retrieve", example = "1")
            @PathVariable("id") @Min(value = 1, message = "Event ID must be greater than 0") Long id
    ) {
        try {
            Event event = eventService.getEventById(id);
            APIResponse<Event> response = new APIResponse<>("The event has been successfully retrieved.", event, "100 CONTINUE");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an event", description = "Updates an existing event by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Event, Venue, or Attendee not found")
    })
    public ResponseEntity<APIResponse<Event>> updateEvent(
            @Parameter(description = "ID of the event to update", example = "1")
            @PathVariable("id") @Min(value = 1, message = "Event ID must be greater than 0") Long id,
            @Valid @RequestBody EventRequest request
    ) {
        try {
            Event updatedEvent = eventService.updateEvent(id, request);
            APIResponse<Event> response = new APIResponse<>("The event has been successfully updated.", updatedEvent, "OK");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an event", description = "Deletes an event by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<APIResponse<String>> deleteEvent(
            @Parameter(description = "ID of the event to delete", example = "1")
            @PathVariable("id") @Min(value = 1, message = "Event ID must be greater than 0") Long id
    ) {
        try {
            eventService.deleteEvent(id);
            APIResponse<String> response = new APIResponse<>("The event has been successfully deleted.", null, "OK");
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