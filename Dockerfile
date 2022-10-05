FROM eclipse-temurin
ENV DB_URL=$DB_URL
ENV DB_USER=$DB_USER
ENV DB_PASSWORD=$DB_PASSWORD
EXPOSE 8080
ADD ./target/trainer-api-0.0.1-SNAPSHOT.jar trainerapi.jar
ENTRYPOINT [ "java", "-jar", "trainerapi.jar", "--spring.profiles.active=prod" ]
