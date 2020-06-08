package com.analyzer.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.analyzer.system.exception.SystemRuntimeException;
import com.analyzer.system.model.RepositorySearchRequest;
import com.analyzer.system.model.RepositorySearchResponse;

import lombok.NonNull;
import reactor.core.publisher.Mono;

@Component
public class RepositoryApi {
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryApi.class);

	private final WebClient webClient;

	private static final int PER_PAGE = 30;
	private static final String SEARCH_REPO_URI = "search/repositories?q=%s&page=%d&per_page=%d";

	@Autowired
	public RepositoryApi(WebClient webClient) {
		super();
        LOGGER.debug("Initializing");
		this.webClient = webClient;
	}

	private String buildQuery(String keyword, int page) {
		return String.format(SEARCH_REPO_URI, keyword, page, PER_PAGE);
		
	}
	
	public RepositorySearchResponse getRepositories(@NonNull  RepositorySearchRequest request) {
		
		ResponseEntity<RepositorySearchResponse> result = null;
		try {
			result = webClient.get().uri(buildQuery(request.getQuery(), request.getPage()))
					.retrieve()
					.toEntity(RepositorySearchResponse.class)
					.onErrorResume(t -> Mono.error(new SystemRuntimeException(t.getMessage())))
					.block();
		} catch (Exception e) {
			throw new SystemRuntimeException(String.format("Failed to query repositories with name - %s", request.getQuery()));
		}
		return result.getBody();
		
	}
	
}