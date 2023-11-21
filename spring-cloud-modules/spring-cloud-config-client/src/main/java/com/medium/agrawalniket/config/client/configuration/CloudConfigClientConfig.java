package com.medium.agrawalniket.config.client.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "cloud.config")
public class CloudConfigClientConfig {
  private String greeting;
}
