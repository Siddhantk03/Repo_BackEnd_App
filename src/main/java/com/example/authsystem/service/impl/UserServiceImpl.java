package com.example.authsystem.service.impl;

import com.example.authsystem.dto.ApiResponse;
import com.example.authsystem.dto.UpdateProfileRequest;
import com.example.authsystem.dto.UserResponse;
import com.example.authsystem.entity.User;
import com.example.authsystem.exception.ResourceNotFoundException;
import com.example.authsystem.exception.UserAlreadyExistsException;
import com.example.authsystem.repository.UserRepository;
import com.example.authsystem.service.UserService;
import com.example.authsystem.util.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserResponse getCurrentUserProfile(String currentUserIdentifier) {
        log.info("Fetching profile for user: {}", currentUserIdentifier);
        User user = findUserByIdentifier(currentUserIdentifier);
        return UserMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateCurrentUserProfile(String currentUserIdentifier, UpdateProfileRequest request) {
        log.info("Updating profile for user: {}", currentUserIdentifier);
        User user = findUserByIdentifier(currentUserIdentifier);

        String newEmail = request.getEmail().trim().toLowerCase();
        if (!user.getEmail().equalsIgnoreCase(newEmail)) {
            if (userRepository.existsByEmailAndIdNot(newEmail, user.getId())) {
                throw new UserAlreadyExistsException("Email '" + newEmail + "' is already in use by another account");
            }
            user.setEmail(newEmail);
        }

        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber().trim() : null);

        User updatedUser = userRepository.save(user);
        log.info("Profile updated successfully for user ID: {}", updatedUser.getId());

        return UserMapper.toUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public ApiResponse<String> deleteCurrentUserProfile(String currentUserIdentifier) {
        log.info("Deleting account for user: {}", currentUserIdentifier);
        User user = findUserByIdentifier(currentUserIdentifier);
        userRepository.delete(user);
        log.info("Account deleted successfully for user ID: {}", user.getId());
        return ApiResponse.success("User account deleted successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        log.info("Retrieving all registered users (Admin action)");
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        log.info("Retrieving user by ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public ApiResponse<String> deleteUserById(Long id) {
        log.info("Deleting user by ID: {} (Admin action)", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return ApiResponse.success("User deleted successfully with ID: " + id);
    }

    private User findUserByIdentifier(String identifier) {
        return userRepository.findByUsernameOrEmail(identifier)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with identifier: " + identifier));
    }
}
