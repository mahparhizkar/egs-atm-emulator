FROM openjdk:8
COPY ./target/info-0.0.1-SNAPSHOT.jar info.jar
ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Tehran","/info.jar"]
EXPOSE 8752 7752