package com.analyzer.api.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.analyzer.api.facade.RepositoryFacade;
import com.analyzer.api.model.CommitStatisticsModel;
import com.analyzer.api.model.ContributorsStatisticsModel;
import com.analyzer.api.model.RepositoryModel;
import com.analyzer.api.model.WeeklyReportModel;
import com.analyzer.core.dto.CommitsStatistics;
import com.analyzer.core.dto.ContributorsStatistics;
import com.analyzer.core.dto.Repository;
import com.analyzer.core.service.CommitsService;
import com.analyzer.core.service.RepositoryService;
import com.analyzer.core.service.StatisticsService;

import ma.glasnost.orika.MapperFacade;

@Component
public class RepositoryFacadeImpl implements RepositoryFacade {

	private final RepositoryService repositoryService;

	private final StatisticsService statisticsService;

	private final CommitsService commitsService;

	private final MapperFacade repositoryModelMapper;
	
	private final MapperFacade statisticsModelMapper;
	
	private final MapperFacade commitsModelMapper;

	@Autowired
	public RepositoryFacadeImpl(RepositoryService repositoryService, 
					StatisticsService statisticsService,
					CommitsService commitsService,
					@Qualifier("repositoryModelMapper") MapperFacade repositoryModelMapper,
					@Qualifier("statisticsModelMapper") MapperFacade statisticsModelMapper,
					@Qualifier("commitsModelMapper") MapperFacade commitsModelMapper) {
		this.statisticsService = statisticsService;
		this.repositoryService = repositoryService;
		this.commitsService = commitsService;
		this.repositoryModelMapper = repositoryModelMapper;
		this.statisticsModelMapper = statisticsModelMapper;
		this.commitsModelMapper = commitsModelMapper;
	}

	@Override
	public List<RepositoryModel> getRepositories(String keyWord, int page) {
		List<Repository> repositories = repositoryService.getRepositories(keyWord, page);
		return repositoryModelMapper.mapAsList(repositories, RepositoryModel.class);
	}

	@Override
	public ContributorsStatisticsModel getContributors(String repoName, String author) {
		ContributorsStatistics statistics = statisticsService.getContributions(repoName, author);
		return statisticsModelMapper.map(statistics, ContributorsStatisticsModel.class);
	}

	@Override
	public CommitStatisticsModel getCommits(String repoName, String author) {
		CommitsStatistics statistics = commitsService.getCommits(repoName, author);
		return commitsModelMapper.map(statistics, CommitStatisticsModel.class);
	}

	@Override
	public WeeklyReportModel getTimeLine(String repoName, String author) {
		CommitStatisticsModel statistics = commitsModelMapper.map(commitsService.getCommits(repoName, author),
				CommitStatisticsModel.class);
		WeeklyReportModel weeklyReportModel = new WeeklyReportModel();
		weeklyReportModel.fill(statistics.getCommitsData());
		return weeklyReportModel;
	}
}
