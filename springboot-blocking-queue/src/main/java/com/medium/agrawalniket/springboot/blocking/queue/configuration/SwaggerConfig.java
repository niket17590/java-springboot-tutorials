package com.medium.agrawalniket.springboot.blocking.queue.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openApiInformation() {
		Server localServer = new Server().url("http://localhost:8080").description("Localhost Server URL");
		Contact contact = new Contact().email("niket.agrawal90@gmail.com").name("Niket Agrawal");
		Info info = new Info().contact(contact).description("Task execution on Single Thread via Queue in Spring Boot")
				.summary("Task execution on Single Thread via Queue in Spring Boot").title("Customized Queue in Spring Boot")
				.version("V1.0.0").license(new License().name("Apache 2.0").url("http://springdoc.org"));

		return new OpenAPI().info(info).addServersItem(localServer);
	}
}
