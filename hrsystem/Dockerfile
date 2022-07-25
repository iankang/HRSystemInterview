FROM openjdk:11
VOLUME /tmp
EXPOSE 8089
COPY target/hrsystem-0.0.1-SNAPSHOT.jar hrsbackend.jar
ENTRYPOINT ["java", "-jar","hrsbackend.jar"]
