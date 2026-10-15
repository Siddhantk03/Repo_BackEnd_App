package com.example.authsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Authentication Response Payload containing JWT token")
public class AuthenticationResponse {

    @Schema(description = "JWT Access Token")
    private String token;

    @Builder.Default
    @Schema(description = "Token Type", example = "Bearer")
    private String type = "Bearer";

    @Schema(description = "Token validity in milliseconds", example = "86400000")
    private long expiresIn;

    @Schema(description = "Authenticated user's username", example = "johndoe")
    private String username;

    @Schema(description = "Authenticated user's email", example = "john.doe@example.com")
    private String email;

    @Schema(description = "User assigned role", example = "USER")
    private String role;

    @Schema(description = "Informational status message", example = "Authentication successful")
    private String message;
}
