package com.example.springhw03.model;



import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Event {
    private Long eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private Long venueId;
    private Venue venue;
    private List<Attendee> attendees;
}