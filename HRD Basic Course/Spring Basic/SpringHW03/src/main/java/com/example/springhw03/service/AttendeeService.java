package com.example.springhw03.service;

import com.example.springhw03.dto.request.AttendeeRequest;
import com.example.springhw03.dto.response.PaginatedResponse;
import com.example.springhw03.mapper.AttendeeMapper;
import com.example.springhw03.mapper.EventAttendeeMapper;
import com.example.springhw03.mapper.EventMapper;
import com.example.springhw03.model.Attendee;
import com.example.springhw03.model.EventAttendee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeMapper attendeeMapper;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventAttendeeMapper eventAttendeeMapper;

    @Transactional
    public Attendee createAttendee(AttendeeRequest request) {
        if (attendeeMapper.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("The email " + request.getEmail() + " is already in use.");
        }

        Attendee attendee = new Attendee();
        attendee.setAttendeeName(request.getAttendeeName());
        attendee.setEmail(request.getEmail());
        attendeeMapper.insert(attendee);

        if (request.getEventsId() != null && !request.getEventsId().isEmpty()) {
            for (Long eventId : request.getEventsId()) {
                if (eventMapper.findById(eventId) == null) {
                    throw new RuntimeException("The event id " + eventId + " has not been founded.");
                }
                EventAttendee eventAttendee = new EventAttendee();
                eventAttendee.setEventId(eventId);
                eventAttendee.setAttendeeId(attendee.getAttendeeId());
                eventAttendeeMapper.insert(eventAttendee);
            }
        }

        return attendeeMapper.findById(attendee.getAttendeeId());
    }

    public PaginatedResponse<Attendee> getAllAttendees(int offset, int limit) {
        List<Attendee> attendees = attendeeMapper.findAllPaginated(offset, limit);
        PaginatedResponse<Attendee> response = new PaginatedResponse<>();
        response.setContent(attendees);
        return response;
    }

    public Attendee getAttendeeById(Long id) {
        Attendee attendee = attendeeMapper.findById(id);
        if (attendee == null) {
            throw new RuntimeException("The attendee id " + id + " has not been founded.");
        }
        return attendee;
    }

    @Transactional
    public Attendee updateAttendee(Long id, AttendeeRequest request) {
        Attendee existingAttendee = attendeeMapper.findById(id);
        if (existingAttendee == null) {
            throw new RuntimeException("The attendee id " + id + " has not been founded.");
        }
        Attendee emailCheck = attendeeMapper.findByEmail(request.getEmail());
        if (emailCheck != null && !emailCheck.getAttendeeId().equals(id)) {
            throw new RuntimeException("The email " + request.getEmail() + " is already in use.");
        }
        existingAttendee.setAttendeeName(request.getAttendeeName());
        existingAttendee.setEmail(request.getEmail());
        attendeeMapper.update(existingAttendee);

        if (request.getEventsId() != null) {
            eventAttendeeMapper.deleteByAttendeeId(id);
            for (Long eventId : request.getEventsId()) {
                if (eventMapper.findById(eventId) == null) {
                    throw new RuntimeException("The event id " + eventId + " has not been founded.");
                }
                EventAttendee eventAttendee = new EventAttendee();
                eventAttendee.setEventId(eventId);
                eventAttendee.setAttendeeId(id);
                eventAttendeeMapper.insert(eventAttendee);
            }
        }

        return attendeeMapper.findById(id);
    }

    @Transactional
    public void deleteAttendee(Long id) {
        int rowsAffected = attendeeMapper.deleteById(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("The attendee id " + id + " has not been founded.");
        }
        eventAttendeeMapper.deleteByAttendeeId(id);
    }
}