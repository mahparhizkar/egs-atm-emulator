FROM openjdk:8
COPY ./target/atm-0.0.1-SNAPSHOT.jar atm.jar
ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Tehran","/atm.jar"]
EXPOSE 8750 7750