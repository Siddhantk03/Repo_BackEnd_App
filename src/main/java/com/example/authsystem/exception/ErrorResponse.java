package com.example.authsystem.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Structured Error Response Details")
public class ErrorResponse {

    @Schema(description = "Timestamp of the error")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP Status Code", example = "400")
    private int status;

    @Schema(description = "HTTP Error Description", example = "Bad Request")
    private String error;

    @Schema(description = "Human-readable error message", example = "Validation failed for one or more fields")
    private String message;

    @Schema(description = "Request URI path where error occurred", example = "/api/auth/register")
    private String path;

    @Schema(description = "Detailed field validation errors (if applicable)")
    private Map<String, String> validationErrors;
}
