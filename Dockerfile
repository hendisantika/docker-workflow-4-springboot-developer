FROM openjdk:latest
LABEL maintainer="hendisantika@yahoo.co.id"
VOLUME /tmp
EXPOSE 8080
ADD target/docker-workflow-4-springboot-developer.jar /opt/app.jar
#ADD gcloud-credential.json /opt/gcloud-credential.json
#ENV GOOGLE_APPLICATION_CREDENTIALS=/opt/gcloud-credential.json
RUN bash -c 'touch /opt/app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app.jar"]