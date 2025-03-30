package com.example.springhw03.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class APIResponse<T> {
    private String message;
    private T payload;
    private String status;
    private LocalDateTime time;

    public APIResponse(String message, T payload, String status) {
        this.message = message;
        this.payload = payload;
        this.status = status;
        this.time = LocalDateTime.now();
    }
}