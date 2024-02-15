FROM openjdk:17-alpine

RUN mkdir "spring"
COPY "target/*.jar" "spring/springMVC-crud-app.jar"
ENTRYPOINT ["java", "-jar", "spring/springMVC-crud-app.jar"]

EXPOSE 8085