FROM adoptopenjdk:16-jdk-hotspot

# Add non-root user
RUN groupadd spring
RUN useradd -g spring spring

# Run app from a non-priveleged user
USER spring:spring

COPY "./target/backend-0.0.1-SNAPSHOT.jar" app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]