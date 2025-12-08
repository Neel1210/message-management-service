FROM eclipse-temurin:21-jdk as final
WORKDIR /app
COPY target/worker-service.jar app.jar
# create non-root user
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
USER appuser
ENTRYPOINT ["java","-jar","/app/app.jar"]