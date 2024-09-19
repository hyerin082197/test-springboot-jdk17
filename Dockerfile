FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /workspace/app

COPY settings.xml /root/.m2/settings.xml

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-slim-buster
RUN mkdir -p /app/bin
COPY --from=build /workspace/app/target/mvp-springboot-jdk17-0.0.1-SNAPSHOT.jar /app/bin/app.jar
CMD java $JAVA_OPTS -jar /app/bin/app.jar
