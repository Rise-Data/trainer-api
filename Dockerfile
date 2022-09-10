FROM eclipse-temurin
EXPOSE 8080
ADD ./target/trainer-api-0.0.1-SNAPSHOT.jar trainerapi.jar
ENTRYPOINT [ "java", "-jar", "trainerapi.jar", "--spring.profiles.active=dev" ]