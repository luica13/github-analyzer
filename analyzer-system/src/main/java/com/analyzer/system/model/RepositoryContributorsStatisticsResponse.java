package com.analyzer.system.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositoryContributorsStatisticsResponse {
	
	private int total; // total commits
	private Author author;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public class Author {
		private String login;
		private String id;

		public Author() {
			super();
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
}
