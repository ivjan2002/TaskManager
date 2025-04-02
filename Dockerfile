FROM openjdk:17-jdk-slim as build

WORKDIR /app

# Kopiraj pom.xml i mvnw datoteke
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Osiguraj da mvnw bude izvršni fajl
RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src /app/src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

# Kopiraj gotov JAR iz build faze
COPY --from=build /app/target/taskmanager-0.0.1-SNAPSHOT.jar /app/taskmanager.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/taskmanager.jar"]
