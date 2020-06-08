//package com.analyzer.api.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
////@Configuration
//@Profile({ "default", "test", "production" })
//@EnableSwagger2
//public class SwaggerConfig {
//
//	@Value("${application.name}")
//	private String applicationName;
//
//	@Value("${application.version}")
//	private String applicationVersion;
//
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any())
//				.build().apiInfo(apiInfo());
//	}
//
//	private ApiInfo apiInfo() {
//
//		return new ApiInfoBuilder()
//				.title(applicationName)
//				.version(applicationVersion)
//				.build();
//
//	}
//
////	@Bean
////	public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint() {
////		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
////		entryPoint.setRealmName("Swagger Realm");
////		return entryPoint;
////	}
//}
