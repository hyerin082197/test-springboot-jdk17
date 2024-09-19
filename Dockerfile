FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /workspace/app

COPY settings.xml /root/.m2/settings.xml

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests -Pwar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.war)

FROM openjdk:17-slim-buster
RUN mkdir -p /app/bin
COPY --from=build /workspace/app/target/*.war /app/bin/app.war
CMD java $JAVA_OPTS -jar /app/bin/app.war
