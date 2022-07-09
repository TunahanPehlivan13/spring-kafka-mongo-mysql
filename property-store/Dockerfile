FROM adoptopenjdk/maven-openjdk11 as builder
WORKDIR /property-store
COPY . /property-store
RUN mvn clean install -DskipTests

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /property-store
ARG JAR_FILE=/property-store/target/*.jar
COPY --from=builder $JAR_FILE /property-store/app.jar

EXPOSE 8080
CMD ["java", "-jar", "/property-store/app.jar"]