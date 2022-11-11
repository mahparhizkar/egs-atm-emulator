FROM openjdk:8
COPY ./target/bank-0.0.1-SNAPSHOT.jar bank.jar
ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Tehran","/bank.jar"]
EXPOSE 8751 7751