package com.example.authsystem.service;

import com.example.authsystem.dto.ApiResponse;
import com.example.authsystem.dto.AuthenticationResponse;
import com.example.authsystem.dto.LoginRequest;
import com.example.authsystem.dto.RegisterRequest;

public interface AuthService {

    ApiResponse<String> register(RegisterRequest request);

    AuthenticationResponse login(LoginRequest request);
}
