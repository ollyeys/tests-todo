#mvn clean test -Dsurefire.suiteXmlFiles=src/main/java/testing.xml

# Base image
FROM markhobson/maven-chrome:latest

# Copy code from local to image
COPY src /app
COPY pom.xml /app

# Specify working directory
WORKDIR /app
# Execute command at creation of image
RUN mvn clean compile
# Command to be executed at start of container
CMD mvn test -Dsurefire.suiteXmlFiles=src/main/java/testing.xml -e
