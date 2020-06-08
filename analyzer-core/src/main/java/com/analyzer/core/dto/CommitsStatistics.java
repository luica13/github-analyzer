package com.analyzer.core.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class CommitsStatistics {

	private int totalCount;
	private List<CommitData> commitsData;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<CommitData> getCommitsData() {
		return commitsData;
	}

	public void setCommitsData(List<CommitData> commitsData) {
		this.commitsData = commitsData;
	}

	public static class CommitData {

		private String author;
		private String message;
		private String email;
		private OffsetDateTime date;

		public CommitData(String author, String email, String message, OffsetDateTime date) {
			this.author = author;
			this.email = email;
			this.message = message;
			this.date = date;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public OffsetDateTime getDate() {
			return date;
		}

		public void setDate(OffsetDateTime date) {
			this.date = date;
		}
	}
}
