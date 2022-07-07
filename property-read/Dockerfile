FROM adoptopenjdk/maven-openjdk11 as builder
WORKDIR /property-read
COPY . /property-read
RUN mvn clean install -DskipTests

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /property-read
ARG JAR_FILE=/property-read/target/*.jar
COPY --from=builder $JAR_FILE /property-read/app.jar

EXPOSE 8080
CMD ["java", "-jar", "/property-read/app.jar"]