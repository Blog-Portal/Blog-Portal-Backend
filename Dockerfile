# Use an OpenJDK base image
FROM openjdk:8

#Author/Owner
LABEL MAINTAINER="Madhur"

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/Blog_Portal_Backend_full-0.0.1-SNAPSHOT.jar Blog_Portal_Backend_full-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "Blog_Portal_Backend_full-0.0.1-SNAPSHOT.jar"]
