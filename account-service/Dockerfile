FROM openjdk
MAINTAINER Joseph <nguleduy@gmail.com>
ADD build/libs/account-service-0.0.1-SNAPSHOT.jar account-service.jar
ENTRYPOINT ["java", "-DDISCOVERY_URL=${DISCOVERY_URL} -jar", "/account-service.jar"]
EXPOSE 2222
