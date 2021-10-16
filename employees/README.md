
- Sample Spring Boot Service to demonstrate REST API implementation of employees resource.
- /employees GET, GET-By-Id, POST, PUT, PATCH, DELETE are implemented.
- Query parameter is also added to get /employees resources (e.g. firstName = 'Avinash' AND lastName = 'C')
- Global Exception handling is present.
- Swagger UI is accessible via link http://localhost:9005/service/api/v1/swagger/swagger-ui.html
- It connects with H2 embedded database, scope of the database is till the application runs
- Compile command - "mvn clean install -DskipTests"
- Running the service - "java -jar target\employees-1.0.jar"
