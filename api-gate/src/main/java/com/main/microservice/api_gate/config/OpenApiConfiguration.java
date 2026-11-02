package com.main.microservice.api_gate.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI(){

        return new OpenAPI()
                .info(new Info().title("API_GATEWAY Service API")
                        .description("Rest API for the API_GATEWAY")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("you can refer to the API_GATEWAY Service Wiki Documentation")
                        .url("https://API_GATEWAY-service-dummy-url.com/docs"));

    }
}
