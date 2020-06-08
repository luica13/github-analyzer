FROM openjdk:11

ARG JAR_FILE=analyzer-api/target/analyzer-api-0.0.3-SNAPSHOT.jar

# cd /usr/local/runme
WORKDIR /usr/local/runme

# copy target/find-links.jar /usr/local/runme/analyzer.jar
COPY ${JAR_FILE} analyzer.jar


# java -jar /usr/local/runme/analyzer.jar
ENTRYPOINT ["java","-jar","analyzer.jar"]