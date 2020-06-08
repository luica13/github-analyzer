package com.analyzer.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.analyzer.system.exception.SystemRuntimeException;
import com.analyzer.system.model.RepositoryContributorsStatisticsResponse;
import com.analyzer.system.model.RepositoryStatisticsRequest;

import lombok.NonNull;
import reactor.core.publisher.Mono;

@Component
public class StatisticsApi {
	private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsApi.class);

	private final WebClient webClient;
	private final String statisticsRepoUri = "repos/%s/%s/stats/contributors";
	

	@Autowired
	public StatisticsApi(WebClient webClient) {
		super();
        LOGGER.debug("Initializing");
		this.webClient = webClient;
	}
	
	public List<RepositoryContributorsStatisticsResponse> getContributors(@NonNull RepositoryStatisticsRequest request) {
		ResponseEntity<List<RepositoryContributorsStatisticsResponse>> result = null;
		try {
			result = webClient.get().uri(String.format(statisticsRepoUri, request.getOwner(), request.getRepo()))
					.retrieve()
					.toEntityList(RepositoryContributorsStatisticsResponse.class)
					.onErrorResume(t -> Mono.error(new SystemRuntimeException(t.getMessage())))
					.block();
		} catch (Exception e) {
			throw new SystemRuntimeException(String.format("Failed to query statistics for repository with name - %s", request.getRepo()));
		}
		
		return result.getBody();
		
	}
}
