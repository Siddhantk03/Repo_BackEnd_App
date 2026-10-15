-- ======================================================
-- Seed Data for User Authentication System
-- ======================================================

USE `auth_db`;

-- Default Admin User (Password: AdminPassword123)
-- BCrypt: $2a$10$wIe1V9iJ3jGv3o7Mv9qVpe.9B9vT1IiqxZ5iC4YqQW.7K2V9B3.j2
INSERT INTO `users` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `phone_number`, `role`, `created_at`, `updated_at`)
VALUES (1, 'Super', 'Admin', 'admin', 'admin@example.com', '$2a$10$wIe1V9iJ3jGv3o7Mv9qVpe.9B9vT1IiqxZ5iC4YqQW.7K2V9B3.j2', '+1234567890', 'ADMIN', NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE `username` = `username`;

-- Default Regular User (Password: UserPassword123)
INSERT INTO `users` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `phone_number`, `role`, `created_at`, `updated_at`)
VALUES (2, 'John', 'Doe', 'johndoe', 'john.doe@example.com', '$2a$10$2B8jB7fux1PjYQ3gU0rE9tLw0bM1uS2dF3gH4jK5O0l42nS1kXh7V', '+1987654321', 'USER', NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE `username` = `username`;
