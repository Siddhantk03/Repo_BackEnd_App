package com.example.authsystem.dto;

import com.example.authsystem.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User Profile Response DTO")
public class UserResponse {

    @Schema(description = "Unique User ID", example = "1")
    private Long id;

    @Schema(description = "First Name", example = "John")
    private String firstName;

    @Schema(description = "Last Name", example = "Doe")
    private String lastName;

    @Schema(description = "Username", example = "johndoe")
    private String username;

    @Schema(description = "Email Address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Phone Number", example = "+1234567890")
    private String phoneNumber;

    @Schema(description = "User Role", example = "USER")
    private Role role;

    @Schema(description = "Account Creation Timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Account Last Update Timestamp")
    private LocalDateTime updatedAt;
}
