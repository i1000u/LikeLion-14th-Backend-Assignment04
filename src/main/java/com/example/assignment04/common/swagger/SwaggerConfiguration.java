package com.example.assignment04.common.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {


    @Bean
    public OpenAPI swaggerConfig() {

        Info info = new Info()
                .title("KBO")
                .description("description : 팀과 선수를 활용한 crud 과제")
                .version("1.0");


        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Development Server");


        Components components = new Components()
                .addSecuritySchemes("bearer-key", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearer-key");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer))
                .components(components)
                .addSecurityItem(securityRequirement);


    }

}
