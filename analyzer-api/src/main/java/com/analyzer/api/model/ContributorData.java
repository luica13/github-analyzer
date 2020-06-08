package com.analyzer.api.model;

public class ContributorData {
	
	private int commitsCounts;
	private String userName;

	public ContributorData(int commitsCounts, String userName) {
		this.commitsCounts = commitsCounts;
		this.userName = userName;
	}

	public int getCommitsCounts() {
		return commitsCounts;
	}

	public void setCommitsCounts(int commitsCounts) {
		this.commitsCounts = commitsCounts;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
