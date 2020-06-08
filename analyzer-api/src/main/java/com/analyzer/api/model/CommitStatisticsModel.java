package com.analyzer.api.model;

import java.util.List;

public class CommitStatisticsModel implements Model {

	private static final long serialVersionUID = 6084411985416290847L;
	
	private int totalCount;
	private List<CommitModel> commitsData;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<CommitModel> getCommitsData() {
		return commitsData;
	}

	public void setCommitsData(List<CommitModel> commitsData) {
		this.commitsData = commitsData;
	}


}
