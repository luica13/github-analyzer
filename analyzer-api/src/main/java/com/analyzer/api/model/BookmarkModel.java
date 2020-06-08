package com.analyzer.api.model;

public class BookmarkModel implements Model {

	private static final long serialVersionUID = -6391462048852360197L;
	
	private String name;
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
