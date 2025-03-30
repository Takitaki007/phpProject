package com.example.springhw03.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {
    private String type = "about:blank";
    private String title;
    private int status;
    private String detail;
    private String instance;
    private LocalDateTime timestamp = LocalDateTime.now();
    private Map<String, String> errors;
}