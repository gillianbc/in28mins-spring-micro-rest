package com.gillianbc.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gillianbc.business.exception.InvalidUserException;
import com.gillianbc.business.exception.UserNotFoundException;

/**
 * The name of the class is GillianBC as this should be used across projects.  i.e.
 * it's an organisation level exception.
 * 
 * We're extending the class provided by spring and putting our own spin on it.
 * Have a look at the class we're extending.  We're effectively overriding the methods
 * in the parent class as we apply our exception to all of them i.e. Exception.class
 * 
 * @RestController annotation is itself annotated with @Controller and @ResponseBody.
 * 
 * The @ControllerAdvice annotation indicates that it should be shared across controller classes.
 * It is a specialization of @Component for classes that declare @ExceptionHandler
 * 
 * To see this error handler in action, send something through that will cause an error
 * e.g. non-existent user http://localhost:8080/users/617
 * 
 * That will cause a UserNotFoundException to be thrown - 404. The exception handlers here
 * are covering all exception types via @ExceptionHandler(Exception.class) and specific exception types
 * via @ExceptionHandler(UserNotFoundException.class) but they all end up with the nice common structure 
 * of ExceptionResponse that we've defined.
 * 
 * 
 * 
 * @author gillianbc
 *
 */
@RestController
@ControllerAdvice
public class GillianBCResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse(
				new Date(), 
				ex.getMessage(),
				request.getDescription(false),
				ex.getStackTrace());
		return new ResponseEntity<Object>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exResponse = new ExceptionResponse(
				new Date(), 
				ex.getMessage(),
				ex.getBindingResult().getFieldError().toString());
		
		return new ResponseEntity<Object>(exResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundExceptions(Exception ex, WebRequest request) throws Exception {
		return new ResponseEntity<Object>(makeExceptionResponse(ex, request), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidUserException.class)
	public final ResponseEntity<Object> handleInvalidDataExceptions(Exception ex, WebRequest request) throws Exception {
		return new ResponseEntity<Object>(makeExceptionResponse(ex, request), HttpStatus.BAD_REQUEST);
	}

	private ExceptionResponse makeExceptionResponse(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), 
				ex.getMessage(),
				request.getDescription(false));
		return exceptionResponse;
	}
	
	
	
	
}
