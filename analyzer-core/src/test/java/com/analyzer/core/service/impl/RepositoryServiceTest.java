package com.analyzer.core.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.analyzer.core.dto.Repository;
import com.analyzer.core.exception.IntegrationException;
import com.analyzer.core.service.RepositoryService;
import com.analyzer.system.exception.SystemRuntimeException;
import com.analyzer.system.model.Repository.Owner;
import com.analyzer.system.model.RepositorySearchResponse;
import com.analyzer.system.service.RepositoryApi;

public class RepositoryServiceTest  extends BaseServiceTest{

	@Mock
	private RepositoryApi repositoryApi;

	private RepositoryService repositoryService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		repositoryService = new GitHubRepositoryService(repositoryApi);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getRepositoriesWhenKeywordNull() {
		String keyword = null;
		repositoryService.getRepositories(keyword, 0);
	}

	@Test(expected = IntegrationException.class)
	public void getRepositoriesWhenExceptionThrown() {
		String keyword = "key";
		when(repositoryApi.getRepositories(any())).thenThrow(SystemRuntimeException.class);
		repositoryService.getRepositories(keyword, 0);
	}

	@Test
	public void getRepositories() {
		String keyword = "key";
		
		RepositorySearchResponse expectedResponse = new RepositorySearchResponse();
		com.analyzer.system.model.Repository repo = new com.analyzer.system.model.Repository();
		repo.setCreated_at(OffsetDateTime.now());
		repo.setOwner(new Owner());
		
		List<com.analyzer.system.model.Repository> expected = Collections.nCopies(10, repo);
		expectedResponse.setItems(expected);
		expectedResponse.setTotal_count(expected.size());

		when(repositoryApi.getRepositories(any())).thenReturn(expectedResponse);

		List<Repository> actual = repositoryService.getRepositories(keyword, 0);

		assertEquals("should be equals", expectedResponse.getTotal_count(), actual.size());
	}

}
