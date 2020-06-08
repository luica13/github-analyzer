package com.analyzer.api.facade;

import java.util.List;

import com.analyzer.api.model.CommitStatisticsModel;
import com.analyzer.api.model.ContributorsStatisticsModel;
import com.analyzer.api.model.RepositoryModel;
import com.analyzer.api.model.WeeklyReportModel;

public interface RepositoryFacade {

	List<RepositoryModel> getRepositories(String keyWord, int page);

	ContributorsStatisticsModel getContributors(String repoName, String author);

	CommitStatisticsModel getCommits(String repoName, String author);

	WeeklyReportModel getTimeLine(String repoName, String author);

}
