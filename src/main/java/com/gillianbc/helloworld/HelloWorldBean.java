package com.gillianbc.helloworld;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelloWorldBean {
	private String message;
	private final int age;
	private final Date date;
	List<String> list = new ArrayList<>();
	private String id;

	public HelloWorldBean(String message) {
		super();
		this.message = message;
		this.date = new Date();
		this.age = 27;
		list.add("elephant");
		list.add("frog");

	}
	
	

	public HelloWorldBean(String message, String id) {
		this(message);
		this.id = id;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + ", age=" + age + ", date=" + date + "]";
	}

	public int getAge() {
		return age;
	}

	public Date getDate() {
		return date;
	}




}
