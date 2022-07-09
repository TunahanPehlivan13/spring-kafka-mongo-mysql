FROM adoptopenjdk/maven-openjdk11 as builder
WORKDIR /property-search
COPY . /property-search
RUN mvn clean install -DskipTests

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /property-search
ARG JAR_FILE=/property-search/target/*.jar
COPY --from=builder $JAR_FILE /property-search/app.jar

EXPOSE 8080
CMD ["java", "-jar", "/property-search/app.jar"]