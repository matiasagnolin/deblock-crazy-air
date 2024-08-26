# Use the official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set the working directory inside the Docker container
WORKDIR /app

# Copy the built jar file to the Docker image
COPY build/libs/flights-service-tough-jet-0.0.1-SNAPSHOT.jar flightservice-toughjet.jar

# Expose the port that the service will run on
EXPOSE 8082

# Set the entry point to run the jar file
ENTRYPOINT ["java", "-jar", "flightservice-toughjet.jar"]
