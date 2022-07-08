FROM adoptopenjdk/maven-openjdk11 as builder
WORKDIR /property-viewer
COPY . /property-viewer
RUN mvn clean install -DskipTests

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /property-viewer
ARG JAR_FILE=/property-viewer/target/*.jar
COPY --from=builder $JAR_FILE /property-viewer/app.jar

EXPOSE 8080
CMD ["java", "-jar", "/property-viewer/app.jar"]