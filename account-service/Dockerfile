FROM openjdk:8-jdk-alpine
MAINTAINER Joseph <nguleduy@gmail.com>
ADD build/libs/account-service-0.0.1-SNAPSHOT.jar account-service.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "/account-service.jar"]
