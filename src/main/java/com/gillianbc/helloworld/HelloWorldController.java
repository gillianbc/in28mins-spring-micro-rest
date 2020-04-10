package com.gillianbc.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	//First way of doing a GET
	@RequestMapping(method=RequestMethod.GET, path="hello")
	public String helloWorld() {
		return "hello world";
	}

	//Better way of doing a GET
	@GetMapping(path="bye")
	public String byebye() {
		return "bye bye bye";
	}

	
	@RequestMapping(method=RequestMethod.GET, path="hello/international")
	public String helloWorldInternational(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	/*
	 For this to work i.e. return a json representation of the object,
	 the class must have a getter for at least one of its propertues.
	 Without a getter, you will get error:
	 No converter found for return value of type: class com.gillianbc.HelloWorldBean
	 The json response will use the available getters
	 It's Jackson and auto-config that do the conversion to JSON
	 Add the JSON Viewer Chrome extension to see it nicely formatted
	 */
	// Getting a bean
	@GetMapping(path="bean")
	public HelloWorldBean beany() {
		return new HelloWorldBean("I am a little bean");
	}

	
	// Getting a bean with a param
	@GetMapping(path = "bean/{id}")
	public HelloWorldBean beany2(@PathVariable	String id) {
		return new HelloWorldBean("I am little bean too ", id) ;
	}


}


