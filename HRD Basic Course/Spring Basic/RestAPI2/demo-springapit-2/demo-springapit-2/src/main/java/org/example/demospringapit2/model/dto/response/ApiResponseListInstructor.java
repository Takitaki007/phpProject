package com.example.api2homework.model.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class ApiResponseListInstructor {
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private HttpStatus status;
    private List<InstructorDto> payload;
}