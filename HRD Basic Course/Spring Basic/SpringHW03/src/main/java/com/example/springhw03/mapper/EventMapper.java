package com.example.springhw03.mapper;

import com.example.springhw03.model.Attendee;
import com.example.springhw03.model.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventMapper {

    @Insert("INSERT INTO events (event_name, event_date, venue_id) VALUES (#{eventName}, #{eventDate}, #{venueId})")
    @Options(useGeneratedKeys = true, keyProperty = "eventId")
    void insert(Event event);

    @Select("SELECT * FROM events WHERE event_id = #{id}")
    @Results(id = "eventResultMap", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "com.example.eventticketing.mapper.VenueMapper.findById")),
            @Result(property = "attendees", column = "event_id", many = @Many(select = "com.example.eventticketing.mapper.AttendeeMapper.findAttendeesByEventId"))
    })
    Event findById(Long id);

    @Select("SELECT * FROM events ORDER BY event_id LIMIT #{limit} OFFSET #{offset}")
    @ResultMap("eventResultMap")
    List<Event> findAllPaginated(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM events")
    long countAll();

    @Update("UPDATE events SET event_name = #{eventName}, event_date = #{eventDate}, venue_id = #{venueId} WHERE event_id = #{eventId}")
    void update(Event event);

    @Delete("DELETE FROM events WHERE event_id = #{id}")
    int deleteById(Long id);

    @Select("SELECT e.* FROM events e JOIN event_attendee ea ON e.event_id = ea.event_id WHERE ea.attendee_id = #{attendeeId}")
    @ResultMap("eventResultMap")
    List<Event> findEventsByAttendeeId(Long attendeeId);

    @Select("SELECT a.* FROM attendees a JOIN event_attendee ea ON a.attendee_id = ea.attendee_id WHERE ea.event_id = #{eventId}")
    @ResultMap("attendeeResultMap")
    List<Attendee> findAttendeesByEventId(Long eventId);
}