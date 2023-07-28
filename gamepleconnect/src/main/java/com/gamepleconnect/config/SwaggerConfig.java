package com.gamepleconnect.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi auth() {
        return GroupedOpenApi.builder()
                .group("Gameple - Auth")
                .pathsToMatch("/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi content() {
        return GroupedOpenApi.builder()
                .group("Gameple - User Content")
                .pathsToMatch("/api/content/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Gameple")
                        .description("Gameple API 명세서")
                        .version("v0.0.1"));
    }
}
