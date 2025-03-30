package com.example.springhw03.mapper;

import com.example.springhw03.model.EventAttendee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EventAttendeeMapper {

    @Insert("INSERT INTO event_attendee (event_id, attendee_id) VALUES (#{eventId}, #{attendeeId})")
    void insert(EventAttendee eventAttendee);

    @Delete("DELETE FROM event_attendee WHERE event_id = #{eventId}")
    void deleteByEventId(Long eventId);

    @Delete("DELETE FROM event_attendee WHERE attendee_id = #{attendeeId}")
    void deleteByAttendeeId(Long attendeeId);
}