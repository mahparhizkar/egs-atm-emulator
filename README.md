# ATM-EMULATOR
Spring boot microservices Atm-Emulator

## Prerequisites
1. Java 8
2. JPA Mysql
3. Spring Cloud
4. Lombok 
5. Eureka discovery server
6. Zuul gateway
7. Admin monitoring services
8. JWT + Session handling
9. Aop Logger
10. Netflix Hystrix
11. Swagger ui


import postman file from asset folder, 
Token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjYXJkTnVtYmVyIjoiMTA1NTAwMDAwMSJ9.ZUG0mBn7o9ECSUgs-rJrFNxfhMx2J9k6Zomz4EZLeio

Strategy:
```
Log strategy:
1. Root path for log is var/log/bank-atm/...
2. ...-all.log is tha full log
3. ...-event.log is the info and event
4. ...-error.log is the error log

CardNumber strategy:
1. Digit count: 10
2. Start with: 10
3. Sum digit 3-7: 10

PinTypes strategy:
1 = Pin,
2 = Finger Print

PinValue strategy:
If pinType=1: Digit count:4
```

## Setup MySQL
Import bank-app.sql to your MySQL

## Admin Service (8759)
Online services monitoring.

## Eureka Server (8761)
Holds the information about all client-service applications.

## Zuul Service (8760)
Create Gateway-Service (Zuul proxy) Application ,register it in Eureka server and centralize authentication incoming request.

## Atm Service (8750)
This service is the user side interface for send the requests to bank service.

## Bank Service (8751)
Is the main part of application for hold the data and control the session and token.

## Info Service (8752)
Is a service for get general info for login like session before login, cardNumber and pinValue , ...




## Deployment Strategy with Docker

### Build image for each microservices
**1. Admin Service**
```
mvn clean install package -DskipTests
docker image build -t admin-service --rm=true .
```

**2. Eureka Service**
```
mvn clean install package -DskipTests
docker image build -t eureka-service --rm=true .
```

**3. Zuul Service**
```
mvn clean install package -DskipTests
docker image build -t zuul-service --rm=true .
```

**4. Atm Service**
```
mvn clean install package -DskipTests
docker image build -t atm-service --rm=true .
```

**5. Bank Service**
```
mvn clean install package -DskipTests
docker image build -t bank-service --rm=true .
```

**6. Info Service**
```
mvn clean install package -DskipTests
docker image build -t info-service --rm=true .
```

### Create and run container using Docker Compose
```docker-compose up --build```

## Sample Payload
1. [Verify CardNumber](asset/atm-verify-card.JPG)
2. [Verify PinValue](asset/atm-verify-pin.JPG)
3. [Operation List](asset/atm-operation.JPG)

## Admin-Eureka
1. [Admin UI](asset/admin.JPG)
2. [Eureka UI](asset/eureka.JPG)

## Swagger
1. [Swagger Ui](asset/swagger.JPG)