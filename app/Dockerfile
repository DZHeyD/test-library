FROM maven:3.8.5-openjdk-17

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

FROM openjdk:17-oracle

# Copy the built JAR file from the previous stage to the container
ARG JAR_FILE=target/*.jar
COPY --from=build ${JAR_FILE} app.jar

# Set the command to run the application
CMD ["java", "-jar", "/app.jar"]
