package com.gamomat.gamomatbe.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Validated
@Configuration
@ConfigurationProperties(prefix="gamomat")
@Data
public class ReelMatrixConfig {

    private List<List<String>> reels;

    private List<List<Integer>> winLines;

    private Map<String, Integer> winAmount;

    @Bean
    public static ReelMatrixConfigValidator configurationPropertiesValidator() {
        return new ReelMatrixConfigValidator();
    }
}
