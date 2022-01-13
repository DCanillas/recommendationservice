FROM openjdk:11
EXPOSE 9090
ADD target/recommendation-service.jar recommendation-service.jar
ENTRYPOINT ["java","-jar","/recommendation-service.jar"]