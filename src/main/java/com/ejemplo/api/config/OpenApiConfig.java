package com.ejemplo.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI pharmacyOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Pharmacy API")
                .description("API REST para gestionar productos de una farmacia")
                .version("v1.0.0"));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }
}
