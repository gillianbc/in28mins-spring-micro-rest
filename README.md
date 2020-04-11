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
We use the following annotations in bean GillianBCResponseEntityExceptionHandler to provide a customised exception handler
- @RestController
- @ControllerAdvice
- @ExceptionHandler

## Validation
We validate the request bodies using the @Valid on the service requests that have bodies.

We define our constraints in the entity beans i.e. User and Post using the javax.validation.constraints from validation-api.jar such as:

- @Size
- @Past

hibernate-validator.jar implements the validations defined in validation-api.jar.

We override the method handleMethodArgumentNotValid() in class GillianBCResponseEntityExceptionHandler to give our own custom error response

# HATEOAS
I've skipped the implementation since the packages used in the course are quite old and a lot has changed since - see https://docs.spring.io/spring-hateoas/docs/current/reference/html/#fundamentals
I'll revisit this if and when the need arises

# Internationalization aka i18n

We can return different responses depending on the requested language.

We set a default locale in the main application class and set up some resources containing the messages we need in different languages.  The base name of the resource file is declared as *message*.
i.e. `message.properties`.

We must then define the others using an underscore and the language e.g. `message_fr.properties`.

In the Hello World controller, we use the @RequestHeader annotation to get the locale from the Accept-Language header of the request.
When we request with Accept-Language = FR, Spring automatically knows to use the `message_fr.properties` file.  (It doesn't have to be a real country code;  Spring is just matching the characters in the header).



