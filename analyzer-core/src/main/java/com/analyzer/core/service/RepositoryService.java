package com.analyzer.core.service;

import java.util.List;

import com.analyzer.core.dto.Repository;

public interface RepositoryService {
	
	List<Repository> getRepositories(String keyword, int page);
	
}
