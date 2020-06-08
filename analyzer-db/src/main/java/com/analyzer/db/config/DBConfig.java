package com.analyzer.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.analyzer.db")
@EntityScan(basePackages="com.analyzer.db")
@EnableJpaAuditing
public class DBConfig {


}
