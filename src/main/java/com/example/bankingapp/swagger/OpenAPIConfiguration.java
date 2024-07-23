package com.example.bankingapp.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfiguration {

   @Bean
   public OpenAPI defineOpenApi() {
       Server server = new Server();
       server.setUrl("http://localhost:9091");
       server.setDescription("Development");

       Contact myContact = new Contact();
       myContact.setName("Kabita Kumari");
       myContact.setEmail("kabita.1822000@gmail.com");

       Info information = new Info()
               .title("Banking Management System API")
               .version("1.0")
               .description("This API exposes endpoints to manage bank.")
               .contact(myContact);
       return new OpenAPI()
    		   .info(information)
    		   .addSecurityItem(new SecurityRequirement().addList("JavaInUseSecurityScheme"))
				.components(new Components().addSecuritySchemes("JavaInUseSecurityScheme", new SecurityScheme()
						.name("JavaInUseSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
    		   .servers(List.of(server));
   }
}
