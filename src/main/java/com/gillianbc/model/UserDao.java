package com.gillianbc.model;

import static java.util.stream.Collectors.toList;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gillianbc.business.exception.InvalidUserException;
import com.gillianbc.business.exception.UserNotFoundException;

@Component
public class UserDao {
	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	
	@Autowired
	PostDao postdao;
	
	static {
		users.add(new User(1,"Adam", new Date()));
		users.add(new User(2,"Eve", new Date()));
		users.add(new User(3,"Jacob", new Date()));
	}
	
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User save(User user) {
		int id = 0;
		
		try {
			 id = user.getId();
			
		} catch (Exception e) {
			user.setId(userCount + 1);
		}
		
		if (duplicateUser(id)) {
			throw new InvalidUserException("There is already a user with this id");
		}
		
		++userCount;
		
		users.add(user);
		return user;
	}
	
	private boolean duplicateUser(int id) {
		System.out.println("THE ID IS "+ id);
		long count = users.stream()
		.filter(user -> user.getId() == id)
		.count();
		System.out.println("THE COUNT IS " + count);
		return count > 0;
	}

	public int delete(int userid) {
		checkUserExists(userid);
		postdao.deleteAllForUser(userid);
		users = users.stream()
				.filter(user -> user.getId() != userid)
				.collect(toList());
				return userid;
	}

	public void checkUserExists(int userid) {
		User user = findOne(userid);
		if (user == null) {
			throw new UserNotFoundException("No such user");
		}
		
	}
	
	
}
