package com.example.springhw03.model;
import lombok.Data;

import java.util.List;

@Data
public class Attendee {
    private Long attendeeId;
    private String attendeeName;
    private String email;
    private List<Event> events;
}