FROM java:8
ADD target/catalog-test-service-0.1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker","/app.jar"]