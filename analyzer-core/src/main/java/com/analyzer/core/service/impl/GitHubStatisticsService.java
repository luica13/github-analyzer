package com.analyzer.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.analyzer.core.dto.ContributorsStatistics;
import com.analyzer.core.dto.ContributorsStatistics.ContributorData;
import com.analyzer.core.exception.IntegrationException;
import com.analyzer.core.service.StatisticsService;
import com.analyzer.system.exception.SystemRuntimeException;
import com.analyzer.system.model.RepositoryContributorsStatisticsResponse;
import com.analyzer.system.model.RepositoryStatisticsRequest;
import com.analyzer.system.service.StatisticsApi;

@Service
public class GitHubStatisticsService implements StatisticsService {

	private final StatisticsApi statisticsApi;

	/**
	 * Initializes a new instance of the class.
	 */
	@Autowired
	public GitHubStatisticsService(StatisticsApi statisticsApi) {
		this.statisticsApi = statisticsApi;
	}

	@Override
	public ContributorsStatistics getContributions(String repoName, String author) {

		Assert.notNull(repoName, "repo Name should not be empty");
		Assert.notNull(author, "author should not be empty");

		RepositoryStatisticsRequest request = new RepositoryStatisticsRequest(author, repoName);

		List<RepositoryContributorsStatisticsResponse> response;
		try {
			response = statisticsApi.getContributors(request);
		} catch (SystemRuntimeException e) {
			throw new IntegrationException("Service Error", e);
		}
		
		return constructResultFromResponse(response);
	}

	private ContributorsStatistics constructResultFromResponse(List<RepositoryContributorsStatisticsResponse> response) {
		ContributorsStatistics statistics = new ContributorsStatistics();
		statistics.setTotalCount(response.size());
		statistics.setContributorsData(response.stream()
				.map(e -> new ContributorData(e.getTotal(), e.getAuthor().getLogin()))
				.collect(Collectors.toList()));
		return statistics;
	}

}
