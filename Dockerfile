FROM openjdk:22
WORKDIR /app
COPY ./target/booktokapi-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "booktokapi-0.0.1-SNAPSHOT.jar"]