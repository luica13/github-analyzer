package com.analyzer.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analyzer.api.facade.RepositoryFacade;
import com.analyzer.api.model.CommitStatisticsModel;
import com.analyzer.api.model.ContributorsStatisticsModel;
import com.analyzer.api.model.RepositoryModel;
import com.analyzer.api.model.WeeklyReportModel;

@RestController
@RequestMapping(value = "/api/v1/repositories")
public class GitRepositoryController {

	private final RepositoryFacade repositoryFacade;

	/**
	 * Initializes a new instance of the class.
	 */
	@Autowired
	public GitRepositoryController(RepositoryFacade repositoryFacade/* , StatisticsService statisticsService */) {
		this.repositoryFacade = repositoryFacade;
	}

	@GetMapping(value = "/search", params = { "keyword", "page" })
	public ResponseEntity<List<RepositoryModel>> getRepositories(@RequestParam("keyword") String keyWord, @RequestParam("page") int page) {
		return ResponseEntity.ok().body(repositoryFacade.getRepositories(keyWord, page));
	}

	@GetMapping(value = "/{repoName}/contributors", params = { "author" })
	public ResponseEntity<ContributorsStatisticsModel> getContributors(@PathVariable("repoName") String repoName, @RequestParam("author") String author) {
		return ResponseEntity.ok().body(repositoryFacade.getContributors(repoName, author));
	}

	@GetMapping(value = "/{repoName}/commits", params = { "author" })
	public ResponseEntity<CommitStatisticsModel> getCommits(@PathVariable("repoName") String repoName, @RequestParam("author") String author) {
		return ResponseEntity.ok().body(repositoryFacade.getCommits(repoName, author));
	}

	@GetMapping(value = "/{repoName}/timeline", params = { "author" })
	public ResponseEntity<WeeklyReportModel> getTimeline(@PathVariable("repoName") String repoName, @RequestParam("author") String author) {
		return ResponseEntity.ok().body(repositoryFacade.getTimeLine(repoName, author));
	}
}
