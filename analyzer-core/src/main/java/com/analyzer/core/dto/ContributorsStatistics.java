package com.analyzer.core.dto;

import java.util.List;

public class ContributorsStatistics {
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

	public static class ContributorData {
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
}
