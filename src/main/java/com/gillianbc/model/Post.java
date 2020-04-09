package com.gillianbc.model;

public class Post {
	private Integer id;
	private Integer userId;
	private String content;
	
	public Post(Integer id, Integer userId, String content) {
		super();
		this.id = id;
		this.userId = userId;
		this.content = content;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}
	public Integer getUserId() {
		return userId;
	}
	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", userId=" + userId + ", content=" + content + "]";
	}
	
	
}
