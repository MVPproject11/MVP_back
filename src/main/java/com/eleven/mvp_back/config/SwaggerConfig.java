package com.eleven.mvp_back.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addResponses("200", new ApiResponse().description("Ok"))
                        .addResponses("201", new ApiResponse().description("Created"))
                        .addResponses("204", new ApiResponse().description("No Content"))
                        .addResponses("400", new ApiResponse().description("Bad Request"))
                        .addResponses("401", new ApiResponse().description("Unauthorized"))
                        .addResponses("403", new ApiResponse().description("Forbidden"))
                        .addResponses("404", new ApiResponse().description("Not Found"))
                        .addResponses("409", new ApiResponse().description("Conflict"))
                        .addResponses("500", new ApiResponse().description("Internal Server Error")))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("블레이버스-Eleven 함께돌봄 REST API Specifications")
                .description("Specification")
                .version("1.0.0");
    }
}
