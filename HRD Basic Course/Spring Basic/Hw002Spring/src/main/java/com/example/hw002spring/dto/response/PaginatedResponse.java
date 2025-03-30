package com.example.hw002spring.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    private List<T> content; // The list of items (instructors) for the current page
    private int page; // Current page number
    private int size; // Number of items per page
    private long totalElements; // Total number of instructors
    private int totalPages; // Total number of pages
}