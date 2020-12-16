FROM openjdk:8-jdk-alpine
MAINTAINER Joseph <nguleduy@gmail.com>
ADD build/libs/*.jar zipkin-service.jar
EXPOSE 9411
ENTRYPOINT ["java", "-jar", "/zipkin-service.jar"]
