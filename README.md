# Spring Boot REST-API Service

Create a simple Rest API which provides a service for storing, updating, retrieving and deleting user entities

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Mysql](https://www.mysql.com/downloads/)

## Running the application locally

First, run this command on your Mysql database for creating database:

```
CREATE DATABASE assignment CHARACTER SET utf8 COLLATE utf8_general_ci;
```

Then, change the `application.properties` file :

```
spring.datasource.username=your_db_user_name
spring.datasource.password=your_db_user_password

logging.file = /your_desired_file_location/assignment.log
```

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the `main` method in the `rest.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Some Endpoints

Here are some endpoints you can call:

#### User login resource

```
POST: /api/v1/auth/login
Accept: application/json
Content-Type: application/json

Reqeust Body:
{
  "password": "password",
  "username": "username"
}

Response:
{
  "AccessToken": "access_token",
  "message": "Login Successfully."
}
```

#### Create an user resource

```
POST: /api/v1/user
Accept: application/json
Content-Type: application/json
Authorization: Bearer $(Access_Token)

Request Body:
{
  "address": "address",
  "emailAddress": "email@example.com",
  "firstName": "Demo",
  "lastName": "User",
  "phone": "12345678",
  "role": [
    "Sales",
    "User"
  ]
}

Response:
{
    "message":"Create Successfully."
}
```

#### Retrieve list of user resource

```
GET: /api/v1/user
Accept: application/json
Content-Type: application/json
Authorization: Bearer $(Access_Token)

Response:
[
  {
    "id": 2,
    "firstName": "Demo",
    "lastName": "User",
    "address": "address",
    "emailAddress": "email@example.com",
    "phone": "12345678",
    "role": [
      "Sales",
      "User"
    ]
  }
]
```

## For Testing API To view Swagger 2 API docs

Run the server and browse to [http://localhost:8070/swagger-ui.html](http://localhost:8090/swagger-ui.html)

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
