FROM openjdk:17-jdk

COPY MVP_back/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]