FROM openjdk:17-alpine
COPY ./node-pipes-0.0.1-SNAPSHOT-boot.jar /node-pipes-0.0.1-SNAPSHOT-boot.jar
ENV DB_HOST=host.docker.internal
ENTRYPOINT ["java", "-jar", "/node-pipes-0.0.1-SNAPSHOT-boot.jar"]