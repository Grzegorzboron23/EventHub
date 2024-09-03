FROM openjdk:17-jdk-alpine
ARG JAR_FILE= target/*.jar
COPY ./target/backend-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java", "-jar", "/myapp.jar"]
