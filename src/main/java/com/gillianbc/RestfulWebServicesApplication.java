package com.gillianbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.UK);
		
		return localeResolver;
		
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource;
	}
	
	@Bean
	public RequestMappingHandlerAdapter requestHandler() {
	    RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    List<MediaType> mediaTypeList = new ArrayList<>();
	    
	    mediaTypeList.add(MediaType.APPLICATION_JSON);
	    mediaTypeList.add(MediaType.APPLICATION_XML);
	    mediaTypeList.add(MediaType.TEXT_HTML);
 	    
	    converter.setSupportedMediaTypes(mediaTypeList);
	    adapter.getMessageConverters().add(converter);
	    return adapter;
	}
	
	

}
