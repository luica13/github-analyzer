package com.analyzer.system.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
public class RepositoryStatisticsRequest {
	
	private String owner;
	private String repo;

	public RepositoryStatisticsRequest(String owner, String repo) {
		super();
		this.owner = owner;
		this.repo = repo;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRepo() {
		return repo;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}
}
