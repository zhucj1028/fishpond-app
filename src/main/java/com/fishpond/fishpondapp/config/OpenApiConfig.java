package com.fishpond.fishpondapp.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("本地开发环境")
                ))
                .info(new Info()
                        .title("鱼塘应用 API")
                        .version("1.0")
                        .description("鱼塘应用接口文档")
                        .contact(new Contact().name("开发团队").email("dev@example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("更多信息")
                        .url("https://example.com/docs"));
    }
}