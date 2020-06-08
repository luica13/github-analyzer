package com.analyzer.core.service;

import com.analyzer.core.dto.CommitsStatistics;

public interface CommitsService {

	CommitsStatistics getCommits(String repoName, String author);
}
