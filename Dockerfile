#mvn clean test -Dsurefire.suiteXmlFiles=src/main/java/testing.xml

# Base image
FROM markhobson/maven-chrome:latest

RUN apt-get update && apt-get install -y \
    xvfb \
    && rm -rf /var/lib/apt/lists/* && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable

ENV CHROME_BIN=/usr/bin/google-chrome
ENV CHROME_PATH=/usr/bin/google-chrome

# Specify working directory
WORKDIR /app

# Copy code from local to image
COPY pom.xml /app
COPY src /app/src

# Execute command at creation of image
RUN mvn clean compile
# Command to be executed at start of container
CMD mvn test -Dsurefire.suiteXmlFiles=/app/src/main/java/testing.xml -e -X
