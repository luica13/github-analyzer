package com.analyzer.core.service;

import com.analyzer.core.dto.ContributorsStatistics;

public interface StatisticsService {
	
	 ContributorsStatistics getContributions(String repoName, String author);

}
