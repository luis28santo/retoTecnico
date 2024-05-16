FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

COPY ./pom.xml .
COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN ./mvnw dependency:go-offline

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/api-fast-0.0.1-SNAPSHOT.jar .
EXPOSE 8095

CMD ["java", "-jar", "api-fast-0.0.1-SNAPSHOT.jar"]