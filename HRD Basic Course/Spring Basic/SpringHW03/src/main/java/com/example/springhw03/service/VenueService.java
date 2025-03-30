package com.example.springhw03.service;

import com.example.springhw03.dto.request.VenueRequest;
import com.example.springhw03.dto.response.PaginatedResponse;
import com.example.springhw03.mapper.VenueMapper;
import com.example.springhw03.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VenueService {

    @Autowired
    private VenueMapper venueMapper;

    @Transactional
    public Venue createVenue(VenueRequest request) {
        Venue venue = new Venue();
        venue.setVenueName(request.getVenueName());
        venue.setLocation(request.getLocation());
        venueMapper.insert(venue);
        return venueMapper.findById(venue.getVenueId());
    }

    public PaginatedResponse<Venue> getAllVenues(int offset, int limit) {
        List<Venue> venues = venueMapper.findAllPaginated(offset, limit);
        PaginatedResponse<Venue> response = new PaginatedResponse<>();
        response.setContent(venues);
        return response;
    }

    public Venue getVenueById(Long id) {
        Venue venue = venueMapper.findById(id);
        if (venue == null) {
            throw new RuntimeException("The venue id " + id + " has not been founded.");
        }
        return venue;
    }

    @Transactional
    public Venue updateVenue(Long id, VenueRequest request) {
        Venue existingVenue = venueMapper.findById(id);
        if (existingVenue == null) {
            throw new RuntimeException("The venue id " + id + " has not been founded.");
        }
        existingVenue.setVenueName(request.getVenueName());
        existingVenue.setLocation(request.getLocation());
        venueMapper.update(existingVenue);
        return venueMapper.findById(id);
    }

    @Transactional
    public void deleteVenue(Long id) {
        int rowsAffected = venueMapper.deleteById(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("The venue id " + id + " has not been founded.");
        }
    }
}