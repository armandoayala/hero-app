# Hero app
This is a sample spring-boot project.

# Main technologies
* Java
* Spring boot
* Docker
* Docker Compose
* Redis

## Requirements
* Java 11
* Apache Maven 3.5.0 or higher
* Docker
* Docker compose

## How to Run
- Run the application
```
docker-compose up --build 
```

- Stop the application
```
docker-compose down 
```
##  Cache redis test
- To test if the cache works, you can call the endpoint **GET api/hero/{id}**
  At first time the call will delay 3 - 4 seconds, but at second time the result will recovery from cache, so you will receive the response in less than 100 milliseconds.
- Please, note that delay was implemented to simulate a big delay and ease the cache test.
- To implement the cache, this project uses Redis.
- To clear the cache, you can call the endpoint **DELETE api/hero/{id}**

##  Reference Documentation
- Swagger UI: http://localhost:8082/api/doc/ui
- Swagger Spec: http://localhost:8082/api/doc/json
- Login: http://localhost:8082/api/login
- Health: http://localhost:8082/api/manage/health
- Info: http://localhost:8082/api/manage/info
- More info: see help.txt

