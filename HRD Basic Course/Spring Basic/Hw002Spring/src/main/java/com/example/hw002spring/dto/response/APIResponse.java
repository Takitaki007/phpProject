package com.example.hw002spring.dto.response;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class APIResponse<T> {
    private String message;
    private T payload;
    private String status;
    private String time;

    public APIResponse(String message, T payload, String status) {
        this.message = message;
        this.payload = payload;
        this.status = status;
        this.time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());
    }
}