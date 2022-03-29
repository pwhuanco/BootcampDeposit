FROM openjdk:11.0-jdk-slim-stretch
VOLUME /tmp
ADD target/bankdeposit-0.0.1-SNAPSHOT.jar deposit.jar
RUN bash -c 'touch /deposit.jar'
ENV JAVA_OPTS="-Djdk.tls.client.protocols=TLSv1.2"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /deposit.jar"]