FROM openjdk:8
COPY ./target/eureka-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java","-jar","-Xms30m","-Xmx50m","-Duser.timezone=Asia/Tehran","/eureka.jar"]
EXPOSE 8761 7761