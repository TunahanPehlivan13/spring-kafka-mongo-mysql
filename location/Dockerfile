FROM adoptopenjdk/maven-openjdk11 as builder
WORKDIR /location
COPY . /location
RUN mvn clean install -DskipTests

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /location
ARG JAR_FILE=/location/target/*.jar
COPY --from=builder $JAR_FILE /location/app.jar

EXPOSE 8080
CMD ["java", "-jar", "/location/app.jar"]