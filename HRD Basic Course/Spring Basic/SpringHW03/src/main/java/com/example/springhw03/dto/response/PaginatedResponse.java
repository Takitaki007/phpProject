package com.example.springhw03.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    private List<T> content;
}