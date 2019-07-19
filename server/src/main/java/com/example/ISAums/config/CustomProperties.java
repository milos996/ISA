package com.example.ISAums.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties({CustomProperties.class})
@ConfigurationProperties(prefix = "properties", ignoreUnknownFields = false)
public class CustomProperties {

    private String jwtSecret;

    private int jwtExpirationInMs;

    private Long maxAgeSecs;

    private String frontBaseUrl;
}
