package com.analyzer.system.config;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ComponentScan(basePackages = "com.github")
@PropertySource("classpath:application.properties")
public class GitHubClientConfiguration {

	@Value("${github.api.url}")
	private String githubBaseUrl;

	@Value("${github.api.token}")
	private String token;

	@Bean
	public WebClient webClient() {
		return WebClient.builder().baseUrl(githubBaseUrl).defaultHeaders(headers()).build();
	}

	public Consumer<HttpHeaders> toAuthorization() {
		return httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
	}

	public Consumer<HttpHeaders> headers() {

		return httpHeaders -> {
			httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
			httpHeaders.add(HttpHeaders.AUTHORIZATION, basicAuthorization(token));
		};
	}

	private static String basicAuthorization(final String token) {
		final byte[] decoded = Base64Utils.decodeFromString(token);
		return String.format("token %s", new String(decoded, StandardCharsets.US_ASCII));
	}
}
