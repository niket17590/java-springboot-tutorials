package com.medium.agrawalniket.springboot.openai.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationBeanConfigurations {

  @Value("${personal.openai.api.token}")
  private String personalOpenAiToken;

  @Bean
  public OpenAPI openApiInformation() {
    Server localServer =
        new Server().url("http://localhost:8080").description("Localhost Server URL");
    Contact contact = new Contact().email("niket.agrawal90@gmail.com").name("Niket Agrawal");
    Info info = new Info().contact(contact).description("Spring Boot 3 + OpenAI ChatGpt")
        .summary("Demo of Spring Boot 3 & OpenAI ChatGpt Integration")
        .title("Spring Boot 3 + OpenAI ChatGpt").version("V1.0.0")
        .license(new License().name("Apache 2.0").url("http://springdoc.org"));

    return new OpenAPI().info(info).addServersItem(localServer);
  }

  @Bean
  @Qualifier("customRestTemplate")
  public RestTemplate customRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add((request, body, execution) -> {
      request.getHeaders().add("Authorization", "Bearer " + personalOpenAiToken);
      return execution.execute(request, body);
    });
    return restTemplate;
  }

}
