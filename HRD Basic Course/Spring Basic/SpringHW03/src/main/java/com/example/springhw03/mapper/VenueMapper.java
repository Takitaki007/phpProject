package com.example.springhw03.mapper;

import com.example.springhw03.model.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueMapper {

    @Insert("INSERT INTO venues (venue_name, location) VALUES (#{venueName}, #{location})")
    @Options(useGeneratedKeys = true, keyProperty = "venueId")
    void insert(Venue venue);

    @Select("SELECT * FROM venues WHERE venue_id = #{id}")
    Venue findById(Long id);

    @Select("SELECT * FROM venues ORDER BY venue_id LIMIT #{limit} OFFSET #{offset}")
    List<Venue> findAllPaginated(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM venues")
    long countAll();

    @Update("UPDATE venues SET venue_name = #{venueName}, location = #{location} WHERE venue_id = #{venueId}")
    void update(Venue venue);

    @Delete("DELETE FROM venues WHERE venue_id = #{id}")
    int deleteById(Long id);
}