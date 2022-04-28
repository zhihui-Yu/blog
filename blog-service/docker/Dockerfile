FROM            adoptopenjdk/openjdk16:alpine-jre
LABEL           app=blog-web
COPY            blog-web-0.0.1.jar /opt/app.jar
# 容器启动时执行
ENTRYPOINT      ["java", "-jar", "/opt/app.jar"]