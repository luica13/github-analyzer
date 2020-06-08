package com.analyzer.system.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
public class RepositorySearchRequest {
	
	private String query;
	private int page;
	

	public RepositorySearchRequest(String query, int page) {
		super();
		this.query = query;
		this.page = page;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
