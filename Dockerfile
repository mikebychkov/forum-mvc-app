FROM openjdk:17-jdk
WORKDIR /app
COPY ./target/forum-1.jar /app/forum.jar
CMD ["java", "-jar", "/app/forum-1.jdk"]
EXPOSE 8080