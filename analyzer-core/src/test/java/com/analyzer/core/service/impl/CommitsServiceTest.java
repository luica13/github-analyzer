package com.analyzer.core.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.analyzer.core.dto.CommitsStatistics;
import com.analyzer.core.exception.IntegrationException;
import com.analyzer.core.service.CommitsService;
import com.analyzer.system.exception.SystemRuntimeException;
import com.analyzer.system.model.Commit;
import com.analyzer.system.model.CommitsStatisticsResponse;
import com.analyzer.system.model.Committer;
import com.analyzer.system.model.RepositoryStatisticsRequest;
import com.analyzer.system.service.CommitsApi;

public class CommitsServiceTest extends BaseServiceTest{
	
	@Mock
	private CommitsApi commitsApi;

	private CommitsService commitsService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		commitsService = new GitHubCommitsService(commitsApi);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getCommitsWhenRepoNameIsNull() {
		String repoName = null;
		commitsService.getCommits(repoName, "someString");
	}

	@Test(expected = IllegalArgumentException.class)
	public void getCommitsWhenAuthorIsNull() {
		String repoName = "testRepo";
		String author = null;
		commitsService.getCommits(repoName, author);
	}

	@Test(expected = IntegrationException.class)
	public void getCommitsWhenExceptionThrown() {
		String repoName = "testRepo";
		String author = "testAuthor";
		when(commitsApi.getCommits(any())).thenThrow(SystemRuntimeException.class);
		commitsService.getCommits(repoName, author);
	}


	@Test
	public void getCommits() {
		String repoName = "testRepo";
		String author = "testAuthor";
		
		Commit commit = new Commit();
		commit.setAuthor(new Committer(author, author, null));
		commit.setCommitter(new Committer(author, author, null));
		CommitsStatisticsResponse expectedStatisticResponse = new CommitsStatisticsResponse(commit);
		
		List<CommitsStatisticsResponse> expectedResponse = Collections.nCopies(10, expectedStatisticResponse);

		CommitsStatistics statistics = new CommitsStatistics();
		
		when(commitsApi.getCommits(any())).thenReturn(expectedResponse);

		CommitsStatistics actual = commitsService.getCommits(repoName, author);

		assertEquals("should be equals", expectedResponse.size(), actual.getTotalCount());
	
	}
}
