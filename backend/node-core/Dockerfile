FROM openjdk:17-alpine
COPY node-core.jar /node-core.jar
ENV DB_HOST=172.17.0.1
ENV CONF_APP_HOST=172.17.0.1:8080
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/node-core.jar"]