package com.example.authsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User Login Request Payload")
public class LoginRequest {

    @NotBlank(message = "Username or email is required")
    @Schema(description = "Username or registered email address", example = "johndoe")
    private String usernameOrEmail;

    @NotBlank(message = "Password is required")
    @Schema(description = "User account password", example = "SecurePassword123")
    private String password;
}
