package com.example.springhw03.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventRequest {
    @NotNull(message = "Event name is required")
    @NotBlank(message = "Event name must not be blank")
    private String eventName;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    private LocalDateTime eventDate;

    @NotNull(message = "Venue ID is required")
    @Min(value = 1, message = "Venue ID must be greater than 0")
    private Long venueId;

    private List<Long> attendeesId;
}