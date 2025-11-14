package com.mobydigital.academy.mobyapp.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User System API")
                        .description("""
                                REST API para la gestión de usuarios del sistema.
                                Incluye operaciones CRUD, búsqueda y validación de credenciales.
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Moby Digital – Voting System Team")
                                .email("adminacademy@mobydigital.com")
                                .url("https://github.com/julianpelle"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación de referencia de OpenAPI")
                        .url("https://swagger.io/docs/"))
                .addTagsItem(new Tag()
                        .name("Users")
                        .description("Operaciones relacionadas con usuarios"))
                .addTagsItem(new Tag()
                        .name("Authentication")
                        .description("Operaciones de login, registro y validación de credenciales"));
    }
}
