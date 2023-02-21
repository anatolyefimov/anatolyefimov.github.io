FROM openjdk:17-alpine
COPY ./node-conf-0.0.1-SNAPSHOT-boot.jar /node-conf-0.0.1-SNAPSHOT-boot.jar
ENV DB_HOST=172.17.0.1
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/node-conf-0.0.1-SNAPSHOT-boot.jar"]