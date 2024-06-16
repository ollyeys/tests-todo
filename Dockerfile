FROM maven:3.9.7

WORKDIR /app

COPY pom.xml /app
COPY src /app/src


RUN mvn clean compile

CMD mvn test -Dsurefire.suiteXmlFiles=/app/src/main/java/testing.xml -e -X