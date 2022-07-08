FROM adoptopenjdk/maven-openjdk11 as builder
WORKDIR /property-seeder
COPY . /property-seeder
RUN mvn clean install -DskipTests

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /property-seeder
ARG JAR_FILE=/property-seeder/target/*.jar
COPY --from=builder $JAR_FILE /property-seeder/app.jar

EXPOSE 8080
CMD ["java", "-jar", "/property-seeder/app.jar"]