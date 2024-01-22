package com.abdelaziz.school.apiConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // Configuration for Swagger:
    @Bean
    OpenAPI openAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("School management system api")
                        .description("An Api for School management system that can manage students, teachers, courses and grades")
                        .version("v1.0"));
    }
}
