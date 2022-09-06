FROM maven:3.8.4-openjdk-11 as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
ADD . $HOME
RUN mvn clean package -DskipTests

FROM openjdk:11
VOLUME /tmp
EXPOSE 8089
COPY --from=build /usr/app/target/hrsystem-0.0.1-SNAPSHOT.jar hrsbackend.jar
ENTRYPOINT ["java", "-jar","hrsbackend.jar"]
