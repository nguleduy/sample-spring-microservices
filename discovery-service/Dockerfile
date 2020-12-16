FROM openjdk:8-jdk-alpine
MAINTAINER Joseph <nguleduy@gmail.com>
ADD build/libs/discovery-service-0.0.1-SNAPSHOT.jar discovery-service.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]

