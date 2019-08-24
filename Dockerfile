FROM java:8-jdk-alpine

COPY ./target/auth-api-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch auth-api-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","auth-api-0.0.1-SNAPSHOT.jar"]