FROM openjdk:17-jdk

COPY MVP_back/build/libs/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]