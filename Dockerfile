FROM openjdk:latest
COPY ./target/group6-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "group6-0.1.0.2-jar-with-dependencies.jar"]
