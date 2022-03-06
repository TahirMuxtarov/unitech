#FROM alpine
#RUN apk add --no-cache openjdk11
#COPY build/libs/unitech-0.0.1-SNAPSHOT.jar /app/
#WORKDIR /app/
#ENTRYPOINT ["java"]
#CMD ["-jar","/app/unitech-0.0.1-SNAPSHOT.jar"]