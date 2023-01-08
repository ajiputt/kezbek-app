package com.telkomsel.kezbek.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfiguration {

        @Bean
        OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .components(
                                                new Components()
                                                                .addSecuritySchemes("bearer-key",
                                                                                new SecurityScheme()
                                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                                .scheme("bearer")
                                                                                                .bearerFormat("JWT")))
                                .addSecurityItem(
                                                new SecurityRequirement()
                                                                .addList("bearer-jwt", Arrays.asList("read", "write"))
                                                                .addList("bearer-key", Collections.emptyList()))
                                .info(getInfo());

        }

        private Info getInfo() {
                return new Info()
                                .title("Cashback Service Swagger")
                                .description("Service for Cashback")
                                .version("1.0")
                                .license(getLicense());
        }

        private License getLicense() {
                return new License()
                                .name("License")
                                .url("#");
        }

}
