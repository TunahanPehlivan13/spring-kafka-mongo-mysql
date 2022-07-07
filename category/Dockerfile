FROM adoptopenjdk/maven-openjdk11 as builder
WORKDIR /category
COPY . /category
RUN mvn clean install -DskipTests

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /category
ARG JAR_FILE=/category/target/*.jar
COPY --from=builder $JAR_FILE /category/app.jar

EXPOSE 8080
CMD ["java", "-jar", "/category/app.jar"]