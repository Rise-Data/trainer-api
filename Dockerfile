FROM eclipse-temurin
ENV DB_URL="jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL"
ENV DB_USER="RM86782"
ENV DB_PASSWORD="281100"
EXPOSE 8080
ADD ./target/trainer-api-0.0.1-SNAPSHOT.jar trainerapi.jar
ENTRYPOINT [ "java", "-jar", "trainerapi.jar", "--spring.profiles.active=prod" ]