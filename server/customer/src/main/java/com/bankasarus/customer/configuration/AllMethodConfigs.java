package com.bankasarus.customer.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AllMethodConfigs {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//     @Bean
//     public CircuitBreakerConfig circuitBreakerRegistry() {
//         return CircuitBreakerConfig.custom()
//                 .failureRateThreshold(20)
//                 .automaticTransitionFromOpenToHalfOpenEnabled(true)
//                 .minimumNumberOfCalls(5)
//                 .build();
//     }
}
