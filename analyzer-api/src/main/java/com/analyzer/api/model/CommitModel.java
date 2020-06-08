package com.analyzer.api.model;

import java.util.Date;

public class CommitModel implements Model {
	private static final long serialVersionUID = 8416894339073633725L;
	
	private String author;
	private String message;
	private Date date;
	private String email;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
