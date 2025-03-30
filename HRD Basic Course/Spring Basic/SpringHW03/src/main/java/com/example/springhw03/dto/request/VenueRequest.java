package com.example.springhw03.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class VenueRequest {
    @NotNull(message = "Venue name is required")
    @NotBlank(message = "Venue name must not be blank")
    private String venueName;

    @NotNull(message = "Location is required")
    @NotBlank(message = "Location must not be blank")
    private String location;
}