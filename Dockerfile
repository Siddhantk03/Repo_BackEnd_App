# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /workspace

# Install bash and utilities for cross-platform wrapper compatibility
RUN apk add --no-cache bash

# Copy maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Convert Windows CRLF line endings and grant execute permissions
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# Copy source code and package application
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Production JRE runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Add unprivileged user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copy built artifact from builder stage
COPY --from=builder /workspace/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
