package com.medium.agrawalniket.springboot.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class SpringBoot3WithOpenApi3Application {

  public static void main(String[] args) {
    SpringApplication.run(SpringBoot3WithOpenApi3Application.class, args);
  }

  @Bean
  public OpenAPI openApiInformation() {
    Server localServer =
        new Server().url("http://localhost:8080").description("Localhost Server URL");
    Contact contact = new Contact().email("niket.agrawal90@gmail.com").name("Niket Agrawal");
    Info info = new Info().contact(contact).description("Spring Boot 3 + Open API 3")
        .summary("Demo of Spring Boot 3 & Open API 3 Integration")
        .title("Spring Boot 3 + Open API 3").version("V1.0.0")
        .license(new License().name("Apache 2.0").url("http://springdoc.org"));

    return new OpenAPI().info(info).addServersItem(localServer);
  }

}
