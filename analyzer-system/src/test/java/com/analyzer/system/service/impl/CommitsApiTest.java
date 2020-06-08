package com.analyzer.system.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.analyzer.system.exception.SystemRuntimeException;
import com.analyzer.system.model.CommitsStatisticsResponse;
import com.analyzer.system.model.RepositoryStatisticsRequest;
import com.analyzer.system.service.CommitsApi;

import reactor.core.publisher.Mono;

public class CommitsApiTest {

	@Mock(answer = Answers.RETURNS_MOCKS)
	private WebClient webClientMock;

	@Mock
	private WebClient.RequestHeadersSpec requestHeadersMock;

	@Mock
	private WebClient.RequestHeadersUriSpec requestHeadersUriMock;

	@Mock
	private WebClient.ResponseSpec responseMock;

	@Mock
	Mono<ResponseEntity<List<CommitsStatisticsResponse>>> mockResponseMono;

	private static final int PER_PAGE = 100;
	private static final String COMMITS_REPO_URI = "repos/%s/%s/commits?per_page=%d";

	private CommitsApi commitsApi;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		commitsApi = new CommitsApi(webClientMock);
	}

	@Test
	public void getCommitsTest() {
		Mockito.reset(webClientMock);

		RepositoryStatisticsRequest request = new RepositoryStatisticsRequest("testOwner", "testRepo");

		List<CommitsStatisticsResponse> expected = new ArrayList<>();
		ResponseEntity<List<CommitsStatisticsResponse>> responseEntity = new ResponseEntity<>(expected, HttpStatus.OK);

		when(webClientMock.get()).thenReturn(requestHeadersUriMock);
		when(requestHeadersUriMock
				.uri(String.format(COMMITS_REPO_URI, request.getOwner(), request.getRepo(), PER_PAGE)))
						.thenReturn(requestHeadersMock);
		when(requestHeadersMock.retrieve()).thenReturn(responseMock);
		when(responseMock.toEntityList(CommitsStatisticsResponse.class)).thenReturn(Mono.just(responseEntity));

		when(mockResponseMono.block()).thenReturn(responseEntity);

		// Run test scenario
		List<CommitsStatisticsResponse> actual = commitsApi.getCommits(request);

		// Verify
		Mockito.verify(webClientMock).get();
		Mockito.verify(webClientMock.get()).uri(Mockito.anyString());

		assertEquals("should be the same", responseEntity.getBody(), actual);
	}

	@Test(expected = SystemRuntimeException.class)
	public void getCommitsTest_fail() {
		Mockito.reset(webClientMock);

		RepositoryStatisticsRequest request = new RepositoryStatisticsRequest("testOwner", "testRepo");

		when(webClientMock.get()).thenReturn(requestHeadersUriMock);
		when(requestHeadersUriMock
				.uri(String.format(COMMITS_REPO_URI, request.getOwner(), request.getRepo(), PER_PAGE)))
						.thenReturn(requestHeadersMock);
		when(requestHeadersMock.retrieve()).thenThrow(RuntimeException.class);

		// Run test scenario
		commitsApi.getCommits(request);

	}

}
