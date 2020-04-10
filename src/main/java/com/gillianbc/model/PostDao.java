package com.gillianbc.model;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gillianbc.business.exception.UserNotFoundException;


@Component
public class PostDao {
	
	@Autowired
	UserDao userdao;
	
	private static List<Post> posts = new ArrayList<>();
	private static int postCount = 3;
	
	static {
		posts.add(new Post(1,1, "I am happy"));
		posts.add(new Post(2,1, "I am awake"));
		posts.add(new Post(3,2, "I am crazy"));
	}
	
	public List<Post> findAll(){
		return posts;
	}
	
	public List<Post> findAllByUser(int userid) {
		// TODO Auto-generated method stub
		return posts.stream()
				.filter(post -> post.getUserId() == userid)
				.collect(toList());
	}
	
	public Post findOne(int id) {
		for (Post post : posts) {
			if (post.getId() == id) {
				return post;
			}
		}
		return null;
	}

	public Post save(Post post) {
		//check user exists
		User user = userdao.findOne(post.getUserId());
		if (user == null) {
			throw new UserNotFoundException("No such user");
		}
		
        ++postCount;
        post.setId(postCount);
		posts.add(post);
		return post;
	}

	

	
	
}
