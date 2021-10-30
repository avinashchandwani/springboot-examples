FROM java:8-jdk-alpine
COPY ./target/tracing-1.0.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch tracing-1.0.jar'
ENTRYPOINT ["java", "-jar","-Xrunjdwp:server=y,transport=dt_socket,address=9101,suspend=n","tracing-1.0.jar"]