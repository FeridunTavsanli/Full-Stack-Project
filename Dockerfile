FROM openjdk:17-jdk-slim
COPY target/repsy-api.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
