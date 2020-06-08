package com.analyzer.system.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositorySearchResponse {
	
	private int total_count;
	private List<Repository> items;

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int totalCount) {
		this.total_count = totalCount;
	}

	public List<Repository> getItems() {
		return items;
	}

	public void setItems(List<Repository> items) {
		this.items = items;
	}
}
