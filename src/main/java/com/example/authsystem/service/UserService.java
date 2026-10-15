package com.example.authsystem.service;

import com.example.authsystem.dto.ApiResponse;
import com.example.authsystem.dto.UpdateProfileRequest;
import com.example.authsystem.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getCurrentUserProfile(String currentUserIdentifier);

    UserResponse updateCurrentUserProfile(String currentUserIdentifier, UpdateProfileRequest request);

    ApiResponse<String> deleteCurrentUserProfile(String currentUserIdentifier);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    ApiResponse<String> deleteUserById(Long id);
}
