package com.victoria.library.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${http://localhost:8080/library}")
    private String Url;
    @Bean
    public OpenAPI myOpenAPI() {
        Server Server = new Server();
        Server.setUrl(Url);
        Server.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("victoriardspaiva@recife.pe.senac.br");
        contact.setName("Victoria Paiva");

        Info info = new Info()
                .title("Projet Library")
                .version("1.0")
                .contact(contact)
                .description("API developed for managing your personal library.").termsOfService("https://www.bezkoder.com/terms");

        return new OpenAPI().info(info).servers(List.of(Server));
    }
}

