# in28mins-spring-micro-rest

First part of course https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud/learn/lecture/8005620#overview

Deeper understanding of REST web services

Ranga's repo is here:  https://github.com/in28minutes/spring-microservices/tree/master/02.restful-web-services

spring-boot-devtools is used which means code changes cause a faster auto-reload (it only reloads my code, not all the unchanged dependencies).  Remember to add the chrome extension RemoteLiveReload if you want it to auto-refresh in the browser (though I've not had much luck with that).

# Running the App
To run, right-click the project and run as Spring Boot App.  It has an inbuilt tomcat server, so you don't need to set up a server in Eclipse.

The app is available on localhost:8080
Try it via Postman e.g. `GET http://localhost:8080/users`

# Spring Exception Handling
We use the following annotations in class GillianBCResponseEntityExceptionHandler to provide a customised exception handler
- @RestController
- @ControllerAdvice
- @ExceptionHandler

## Validation
We validate the request bodies using the @Valid on the service requests that have bodies.

We define our constraints in the entity classes i.e. User and Post using the javax.validation.constraints such as:

- @Size
- @Past

We override the method handleMethodArgumentNotValid() in class GillianBCResponseEntityExceptionHandler to give our own custom error response
