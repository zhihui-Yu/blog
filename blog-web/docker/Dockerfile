FROM        adoptopenjdk/openjdk16:alpine-jre
LABEL       app=blog-web
COPY        app.jar /opt/app.jar
RUN         ["java", "-jar", "/opt/app.jar"]