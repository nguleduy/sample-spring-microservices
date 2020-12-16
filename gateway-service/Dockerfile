FROM openjdk:8-jdk-alpine
MAINTAINER Joseph <nguleduy@gmail.com>
ADD build/libs/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]

