FROM openjdk:8
COPY ./target/admin-0.0.1-SNAPSHOT.jar admin.jar
ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Tehran","/admin.jar"]
EXPOSE 8759 7759