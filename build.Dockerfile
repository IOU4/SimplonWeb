FROM gradle:7.5.1-jdk17-alpine
WORKDIR /SimplonClone
COPY . .
RUN gradle jar --no-daemon
CMD ["java", "-jar", "build/libs/SimplonClone.jar"]
