package com.telkomsel.kezbek.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {

        @Bean
        OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(getInfo());
        }

        private Info getInfo() {
                return new Info()
                                .title("Authorization Service Swagger")
                                .description("Service for Authorization")
                                .version("1.0")
                                .license(getLicense());
        }

        private License getLicense() {
                return new License()
                                .name("License")
                                .url("#");
        }

}
