# URL shortener
## Review
This is an app to implement Web API for entering project data
into the database (task tracker)

### Technology stack
* Java 17
* Spring Boot
* Maven
* Lombok
* Model mapper
* Flyway
* Postgresql
* Log4j
* Junit5

## How to run
* Clone repository
* You should have docker and maven
* Just use start.sh
* To stop application use stop.sh

## Endpoints
To see endpoints run application and use http://localhost:7070/swagger-ui/index.html#/  
This is an Api documentation

Example for creating of project json  
{
"projectName" : "Temp5",
"completionDate" : "12-12-2023 15:53",
"status" : "Active",
"priority" : "1"  
}

Example for creating of task json  
{  
"taskName" : "Temp1",
"taskStatus" : "ToDo",
"priority" : "10",
"taskDescription" : "Test description",
"project" : {
"id": 1
}  
}





