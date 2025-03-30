package com.example.springhw03.mapper;

import com.example.springhw03.model.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeMapper {

    @Insert("INSERT INTO attendees (attendee_name, email) VALUES (#{attendeeName}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "attendeeId")
    void insert(Attendee attendee);

    @Select("SELECT * FROM attendees WHERE attendee_id = #{id}")
    @Results(id = "attendeeResultMap", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "events", column = "attendee_id", many = @Many(select = "com.example.eventticketing.mapper.EventMapper.findEventsByAttendeeId"))
    })
    Attendee findById(Long id);

    @Select("SELECT * FROM attendees ORDER BY attendee_id LIMIT #{limit} OFFSET #{offset}")
    @ResultMap("attendeeResultMap")
    List<Attendee> findAllPaginated(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM attendees")
    long countAll();

    @Update("UPDATE attendees SET attendee_name = #{attendeeName}, email = #{email} WHERE attendee_id = #{attendeeId}")
    void update(Attendee attendee);

    @Delete("DELETE FROM attendees WHERE attendee_id = #{id}")
    int deleteById(Long id);

    @Select("SELECT * FROM attendees WHERE email = #{email}")
    Attendee findByEmail(String email);
}