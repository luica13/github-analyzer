package com.analyzer.system.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
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
			httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
		};
	}

//	private static String basicAuthorization(final String token) {
//
//		final byte[] basicAuthValue = token.getBytes(StandardCharsets.UTF_8);
//		final String encoded = Base64Utils.encodeToString(basicAuthValue);
//
//		return String.format("Basic %s", encoded);
//	}
}
