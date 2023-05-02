# User Task AOP API Project

This application starts on port 7070. 

**Application's Endpoints are:**

Retrieve Task URI: http://localhost:7070/v1/tasks/1

Retrieve All Tasks URI: http://localhost:7070/v1/tasks

Create Task URI: http://localhost:7070/v1/tasks

Update Task URI: http://localhost:7070/v1/tasks/1

Delete Task URI: http://localhost:7070/v1/tasks/1


This User Task API makes use of below technologies:

+ Spring Boot
+ Spring AOP Framework
+ Swagger
+ H2 in-memory database
+ Maven build tool
+ SonarQube Tool (Static Code Analysis)
+ Transaction Management
+ Caching
+ ETags (Entity Tags)

Swagger 2 URI: http://localhost:7070/swagger-ui/index.html

H2 Database URI: http://localhost:7070/h2-console/

H2 database is initialized with some data by using data.sql file

**Request Payload:**

{

    "title": "Eating",
    
    "description": "Eating Mushroom",
    
    "startDate": "2008-03-25",
    
    "endDate": "2010-11-06",
    
    "taskStatus": "PENDING"
    
}




