package com.analyzer.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.analyzer.core.dto.Repository;
import com.analyzer.core.exception.IntegrationException;
import com.analyzer.core.service.RepositoryService;
import com.analyzer.system.exception.SystemRuntimeException;
import com.analyzer.system.model.RepositorySearchRequest;
import com.analyzer.system.model.RepositorySearchResponse;
import com.analyzer.system.service.RepositoryApi;

@Service
public class GitHubRepositoryService implements RepositoryService {

	private final RepositoryApi repositoryApi;

	/**
	 * Initializes a new instance of the class.
	 */
	@Autowired
	public GitHubRepositoryService(RepositoryApi repositoryApi) {
		this.repositoryApi = repositoryApi;
	}

	@Override
	public List<Repository> getRepositories(String keyword, int page) {
		
		Assert.notNull(keyword, "keyword should not be empty");

		RepositorySearchRequest request = new RepositorySearchRequest(keyword, page);
		
		RepositorySearchResponse response;
		try {
			response = repositoryApi.getRepositories(request);
		} catch (SystemRuntimeException e) {
			throw new IntegrationException("Service Error", e);
		}
		
		return constructResultFromResponse(response);
	}

	private List<Repository> constructResultFromResponse(RepositorySearchResponse response) {

		List<Repository> result = new ArrayList<>();
		result.addAll(response.getItems().stream().map(e -> convertToModel(e)).collect(Collectors.toList()));
		return result;
	}

	private Repository convertToModel(com.analyzer.system.model.Repository e) {
		Repository repo = new Repository();
		repo.setId(e.getId());
		repo.setName(e.getName());
		repo.setDescription(e.getDescription());
		repo.setTitle(e.getFull_name());
		repo.setDateCreated(Date.from(e.getCreated_at().toInstant()));
		repo.setUrl(e.getHtml_url());
		repo.setOwner(e.getOwner().getLogin());
		return repo;
	}

}
