FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD target/traffic-management-service.jar traffic-management-service.jar
ENTRYPOINT ["java", "-jar", "/traffic-management-service.jar"]
