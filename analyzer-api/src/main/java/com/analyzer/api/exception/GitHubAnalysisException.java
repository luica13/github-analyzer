package com.analyzer.api.exception;

public class GitHubAnalysisException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7041457626558321657L;

	public GitHubAnalysisException() {
		super();
	}

	public GitHubAnalysisException(String message) {
		super(message);
	}

	public GitHubAnalysisException(String message, Throwable cause) {
		super(message, cause);
	}
}
