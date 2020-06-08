package com.analyzer.system.model;

public class CommitsStatisticsResponse {
	private Commit commit;

	public CommitsStatisticsResponse() {
		super();
	}

	public CommitsStatisticsResponse(Commit commit) {
		super();
		this.commit = commit;
	}

	public Commit getCommit() {
		return commit;
	}

	public void setCommit(Commit commit) {
		this.commit = commit;
	}
//
//	public Committer getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(Committer author) {
//		this.author = author;
//	}
//
//	public Committer getCommitter() {
//		return committer;
//	}
//
//	public void setCommitter(Committer committer) {
//		this.committer = committer;
//	}

}
