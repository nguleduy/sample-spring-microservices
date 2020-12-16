FROM openjdk:8-jdk-alpine
MAINTAINER Joseph <nguleduy@gmail.com>
ADD build/libs/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar
EXPOSE 3333
ENTRYPOINT ["java", "-jar", "/customer-service.jar"]
