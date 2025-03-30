package com.example.springhw03.service;

import com.example.springhw03.dto.request.EventRequest;
import com.example.springhw03.dto.response.PaginatedResponse;
import com.example.springhw03.mapper.AttendeeMapper;
import com.example.springhw03.mapper.EventAttendeeMapper;
import com.example.springhw03.mapper.EventMapper;
import com.example.springhw03.mapper.VenueMapper;
import com.example.springhw03.model.Event;
import com.example.springhw03.model.EventAttendee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private VenueMapper venueMapper;

    @Autowired
    private AttendeeMapper attendeeMapper;

    @Autowired
    private EventAttendeeMapper eventAttendeeMapper;

    @Transactional
    public Event createEvent(EventRequest request) {
        if (venueMapper.findById(request.getVenueId()) == null) {
            throw new RuntimeException("The venue id " + request.getVenueId() + " has not been founded.");
        }

        Event event = new Event();
        event.setEventName(request.getEventName());
        event.setEventDate(request.getEventDate());
        event.setVenueId(request.getVenueId());
        eventMapper.insert(event);

        if (request.getAttendeesId() != null && !request.getAttendeesId().isEmpty()) {
            for (Long attendeeId : request.getAttendeesId()) {
                if (attendeeMapper.findById(attendeeId) == null) {
                    throw new RuntimeException("The attendee id " + attendeeId + " has not been founded.");
                }
                EventAttendee eventAttendee = new EventAttendee();
                eventAttendee.setEventId(event.getEventId());
                eventAttendee.setAttendeeId(attendeeId);
                eventAttendeeMapper.insert(eventAttendee);
            }
        }

        return eventMapper.findById(event.getEventId());
    }

    public PaginatedResponse<Event> getAllEvents(int offset, int limit) {
        List<Event> events = eventMapper.findAllPaginated(offset, limit);
        PaginatedResponse<Event> response = new PaginatedResponse<>();
        response.setContent(events);
        return response;
    }

    public Event getEventById(Long id) {
        Event event = eventMapper.findById(id);
        if (event == null) {
            throw new RuntimeException("The event id " + id + " has not been founded.");
        }
        return event;
    }

    @Transactional
    public Event updateEvent(Long id, EventRequest request) {
        Event existingEvent = eventMapper.findById(id);
        if (existingEvent == null) {
            throw new RuntimeException("The event id " + id + " has not been founded.");
        }
        if (venueMapper.findById(request.getVenueId()) == null) {
            throw new RuntimeException("The venue id " + request.getVenueId() + " has not been founded.");
        }
        existingEvent.setEventName(request.getEventName());
        existingEvent.setEventDate(request.getEventDate());
        existingEvent.setVenueId(request.getVenueId());
        eventMapper.update(existingEvent);

        if (request.getAttendeesId() != null) {
            eventAttendeeMapper.deleteByEventId(id);
            for (Long attendeeId : request.getAttendeesId()) {
                if (attendeeMapper.findById(attendeeId) == null) {
                    throw new RuntimeException("The attendee id " + attendeeId + " has not been founded.");
                }
                EventAttendee eventAttendee = new EventAttendee();
                eventAttendee.setEventId(id);
                eventAttendee.setAttendeeId(attendeeId);
                eventAttendeeMapper.insert(eventAttendee);
            }
        }

        return eventMapper.findById(id);
    }

    @Transactional
    public void deleteEvent(Long id) {
        int rowsAffected = eventMapper.deleteById(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("The event id " + id + " has not been founded.");
        }
    }
}