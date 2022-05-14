FROM openjdk:11
VOLUME /main-app
ADD target/school-0.0.1-SNAPSHOT.jar school.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/school.jar"]
