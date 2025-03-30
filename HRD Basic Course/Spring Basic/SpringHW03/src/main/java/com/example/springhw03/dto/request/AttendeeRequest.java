package com.example.springhw03.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AttendeeRequest {
    @NotNull(message = "Attendee name is required")
    @NotBlank(message = "Attendee name must not be blank")
    private String attendeeName;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be a valid email address")
    private String email;

    private List<Long> eventIds;


}