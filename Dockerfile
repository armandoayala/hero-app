FROM adoptopenjdk/maven-openjdk11 AS MAVEN_BUILD

MAINTAINER Armando Ayala

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM adoptopenjdk/openjdk11

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/hero-1.0.0.jar /app/

ENTRYPOINT ["java", "-jar", "hero-1.0.0.jar"]