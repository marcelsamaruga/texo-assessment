FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER github.com/marcelsamaruga
VOLUME /tmp
WORKDIR /opt/app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]