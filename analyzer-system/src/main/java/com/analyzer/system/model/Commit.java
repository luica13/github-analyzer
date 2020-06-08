package com.analyzer.system.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Commit {
	
	private Committer author;
	private Committer committer;
	private String message;

	public Committer getAuthor() {
		return author;
	}

	public void setAuthor(Committer author) {
		this.author = author;
	}

	public Committer getCommitter() {
		return committer;
	}

	public void setCommitter(Committer committer) {
		this.committer = committer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
