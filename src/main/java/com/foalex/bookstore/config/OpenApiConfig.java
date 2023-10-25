package com.foalex.bookstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Value("${bookstore.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI customOpenApi() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL [Development]");

        Contact contact = new Contact();
        contact.setEmail("oleksandr.ellie.mykhailov@gmail.com");
        contact.setName("Oleksandr Mykhailov");

        Info info = new Info()
                .title("Online Book Store Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints of the Online Book Store.");

        return new OpenAPI().info(info).servers(List.of((devServer)));
    }
}
