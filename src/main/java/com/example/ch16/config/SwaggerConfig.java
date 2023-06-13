package com.example.ch16.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static org.springframework.security.config.Elements.JWT;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {

        Components components = new Components().addSecuritySchemes(JWT, getJwtSecurityScheme());
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(JWT);
        Info info = new Info().version("v1.0.0").title("API").description("Spring JPA Project");
        return new OpenAPI().components(components).addSecurityItem(securityRequirement).info(info);

    }
    private SecurityScheme getJwtSecurityScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("X-AUTH-TOKEN");
    }




}
