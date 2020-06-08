package com.analyzer.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.analyzer.system.exception.SystemRuntimeException;
import com.analyzer.system.model.CommitsStatisticsResponse;
import com.analyzer.system.model.RepositoryStatisticsRequest;

import lombok.NonNull;
import reactor.core.publisher.Mono;

@Component
public class CommitsApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommitsApi.class);

	private final WebClient webClient;

	private static final int PER_PAGE = 100;
	private static final String COMMITS_REPO_URI = "repos/%s/%s/commits?per_page=%d";

	@Autowired
	public CommitsApi(WebClient webClient) {
		super();
        LOGGER.debug("Initializing...");
		this.webClient = webClient;
	}
	
	public List<CommitsStatisticsResponse> getCommits(@NonNull RepositoryStatisticsRequest request) {
		Mono<ResponseEntity<List<CommitsStatisticsResponse>>> result = null;
		try {
			result = webClient.get().uri(String.format(COMMITS_REPO_URI, request.getOwner(), request.getRepo(), PER_PAGE))
					.retrieve()
					.toEntityList(CommitsStatisticsResponse.class)
					.onErrorResume(t -> Mono.error(new SystemRuntimeException(t.getMessage())));
		} catch (Exception e) {
			throw new SystemRuntimeException(String.format("Failed to query statistics for repository with name - %s", request.getRepo()));
		}
		
		return result.block().getBody();
	}
}
