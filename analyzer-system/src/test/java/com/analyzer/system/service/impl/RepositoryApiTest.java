package com.analyzer.system.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

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
import com.analyzer.system.model.Repository;
import com.analyzer.system.model.RepositorySearchRequest;
import com.analyzer.system.model.RepositorySearchResponse;
import com.analyzer.system.service.RepositoryApi;

import reactor.core.publisher.Mono;

public class RepositoryApiTest {

	@Mock(answer = Answers.RETURNS_MOCKS )
	private WebClient webClientMock;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;

    @Mock
    private WebClient.ResponseSpec responseMock;
    
    @Mock
	Mono<ResponseEntity<RepositorySearchResponse>> mockResponseMono;
    
	private final int perPage = 30;
	private final String searchRepoUri = "search/repositories?q=%s&page=%d&per_page=%d";
    
	private RepositoryApi repositoryApi;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		repositoryApi = new RepositoryApi(webClientMock);
	}

	@Test
	public void getRepositoriesTest() {
		Mockito.reset(webClientMock);
		
		RepositorySearchRequest request = new RepositorySearchRequest("test", 100);
		
		RepositorySearchResponse mockResponse = new RepositorySearchResponse();
		mockResponse.setTotal_count(1);
		mockResponse.setItems(Collections.singletonList(new Repository()));

		ResponseEntity<RepositorySearchResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
		
		when(webClientMock.get()).thenReturn(requestHeadersUriMock);
		when(requestHeadersUriMock.uri(buildQuery(request.getQuery(), request.getPage()))).thenReturn(requestHeadersMock);
		when(requestHeadersMock.retrieve()).thenReturn(responseMock);
		when(responseMock.toEntity(RepositorySearchResponse.class)).thenReturn(Mono.just(responseEntity));
		when(mockResponseMono.block()).thenReturn(responseEntity);

		// Run test scenario
		RepositorySearchResponse actual = repositoryApi.getRepositories(request);

		// Verify
		Mockito.verify(webClientMock).get();
		Mockito.verify(webClientMock.get()).uri(Mockito.anyString());

		assertEquals("should be the same",  responseEntity.getBody(), actual);
	}

	@Test(expected = SystemRuntimeException.class)
	public void getRepositoriesTest_fail() {
		Mockito.reset(webClientMock);
		
		RepositorySearchRequest request = new RepositorySearchRequest("test", 100);
		
		RepositorySearchResponse mockResponse = new RepositorySearchResponse();
		mockResponse.setTotal_count(1);
		mockResponse.setItems(Collections.singletonList(new Repository()));

		when(webClientMock.get()).thenReturn(requestHeadersUriMock);
		when(requestHeadersUriMock.uri(buildQuery(request.getQuery(), request.getPage()))).thenReturn(requestHeadersMock);
		when(requestHeadersMock.retrieve()).thenThrow(RuntimeException.class);
		
		// Run test scenario
		repositoryApi.getRepositories(request);

	}

	private String buildQuery(String keyword, int page) {
		return String.format(searchRepoUri, keyword, page, perPage);
	}
}
