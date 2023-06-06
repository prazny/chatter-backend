# Java image
FROM openjdk:17-jdk-slim-buster

# curl
RUN apt-get update && apt-get install

# Maven image
FROM maven:3.9.2-eclipse-temurin-17


# maven env
ENV MAVEN_HOME /opt/maven
ENV PATH $MAVEN_HOME/bin:$PATH

# Set working directory in container
WORKDIR /app

# Copy pom
COPY pom.xml .

# Copy src
COPY src ./src


# Download maven deps
RUN mvn -f pom.xml clean package -DskipTests

RUN echo $(ls -la)
RUN echo $(ls -la ./target)
RUN echo $(ls -la /app/target)

# Copy jar file to main direct.
RUN cp /app/target/*.jar /app/app.jar



ENTRYPOINT ["java","-jar","app.jar"]