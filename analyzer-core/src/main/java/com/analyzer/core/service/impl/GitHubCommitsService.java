package com.analyzer.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.analyzer.core.dto.CommitsStatistics;
import com.analyzer.core.dto.CommitsStatistics.CommitData;
import com.analyzer.core.exception.IntegrationException;
import com.analyzer.core.service.CommitsService;
import com.analyzer.system.exception.SystemRuntimeException;
import com.analyzer.system.model.CommitsStatisticsResponse;
import com.analyzer.system.model.RepositoryStatisticsRequest;
import com.analyzer.system.service.CommitsApi;

@Service
public class GitHubCommitsService implements CommitsService{

	private final CommitsApi commitsApi;

	@Autowired
	public GitHubCommitsService(CommitsApi commitsApi) {
		this.commitsApi = commitsApi;
	}

	@Override
	public CommitsStatistics getCommits(String repoName, String author) {

		Assert.notNull(repoName, "Repo Name should not be empty");
		Assert.notNull(author, "Author should not be empty");

		RepositoryStatisticsRequest request = new RepositoryStatisticsRequest(author, repoName);

		List<CommitsStatisticsResponse> response = null;
		try {
			response = commitsApi.getCommits(request);
		} catch (SystemRuntimeException e) {
			throw new IntegrationException("Service Error", e);
		}

		return constructResultFromResponse(response);
	}

	private CommitsStatistics constructResultFromResponse(List<CommitsStatisticsResponse> response) {
		response.get(0);
		CommitsStatistics statistics = new CommitsStatistics();
		statistics.setTotalCount(response.size());
		statistics
				.setCommitsData(
						response.stream()
								.map(e -> new CommitData(e.getCommit().getAuthor().getName(), 
														e.getCommit().getAuthor().getEmail(),
														e.getCommit().getMessage(), 
														e.getCommit().getAuthor().getDate()))
								.collect(Collectors.toList()));
		return statistics;
	}
}
