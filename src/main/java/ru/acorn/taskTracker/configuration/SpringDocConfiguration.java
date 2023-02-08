package ru.acorn.taskTracker.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI taskTrackerAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task tracker API")
                        .description("This is a sample API for the demonstration of task tracker")
                        .version("v0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("Task Tracker API GitHub")
                        .url("https://github.com/AntonAcorn/TaskTracker"));
    }
}
