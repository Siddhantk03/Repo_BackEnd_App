package com.example.authsystem.config;

import com.example.authsystem.entity.Role;
import com.example.authsystem.entity.User;
import com.example.authsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Seed default Admin if not present
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .firstName("Super")
                    .lastName("Admin")
                    .username("admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("AdminPassword123"))
                    .phoneNumber("+1234567890")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(admin);
            log.info("Default ADMIN user initialized: username='admin', email='admin@example.com', password='AdminPassword123'");
        }

        // Seed sample USER if not present
        if (!userRepository.existsByUsername("johndoe")) {
            User testUser = User.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .username("johndoe")
                    .email("john.doe@example.com")
                    .password(passwordEncoder.encode("UserPassword123"))
                    .phoneNumber("+1987654321")
                    .role(Role.USER)
                    .build();

            userRepository.save(testUser);
            log.info("Sample USER initialized: username='johndoe', email='john.doe@example.com', password='UserPassword123'");
        }
    }
}
