# ---------- Builder stage ----------
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copy Maven wrapper and pom
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Make mvnw executable (important on Linux)
RUN chmod +x mvnw

# Warm up dependencies (faster rebuilds)
RUN ./mvnw -q -B dependency:go-offline

# Copy source and build
COPY src ./src
RUN ./mvnw -q -B package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy built jar from builder
COPY --from=builder /app/target/message-management-service-0.0.1-SNAPSHOT.jar app.jar

# Non-root user
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
USER appuser

# Optional, but good practice
EXPOSE 8081

ENTRYPOINT ["java","-jar","/app/app.jar"]