FROM openjdk:20
COPY src/main/java/com/javadeveloperzone/SpringBootConfig.java /tmp
WORKDIR /tmp
CMD java SpringBootConfig.java