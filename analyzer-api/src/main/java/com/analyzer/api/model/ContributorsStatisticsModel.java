package com.analyzer.api.model;

import java.util.List;

public class ContributorsStatisticsModel implements Model {
	private static final long serialVersionUID = -37880014997108183L;

	private int totalCount;
	private List<ContributorData> contributorsData;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ContributorData> getContributorsData() {
		return contributorsData;
	}

	public void setContributorsData(List<ContributorData> contributorsData) {
		this.contributorsData = contributorsData;
	}
}
