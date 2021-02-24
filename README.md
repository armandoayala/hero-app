# Hero app
This is a sample spring-boot project.

## Requirements
* Java 11
* Apache Maven 3.5.0 or higher
* Docker
* Docker compose

## How to Run
- Clone the project
- Build the project
```
mvn clean install
```
- Run the application
```
java -jar target/hero-1.0.0.jar
```

## Using docker
- Run the application
```
docker-compose up --build 
```

- Stop the application
```
docker-compose down 
```

##  Reference Documentation
- Swagger UI: http://localhost:8082/api/doc/ui
- Swagger Spec: http://localhost:8082/api/doc/json
- Login: http://localhost:8082/api/login
- Health: http://localhost:8082/api/manage/health
- Info: http://localhost:8082/api/manage/info
- More info: see help.txt

