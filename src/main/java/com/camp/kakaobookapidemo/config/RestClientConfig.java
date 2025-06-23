package com.camp.kakaobookapidemo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(KakaoProperties.class)
public class RestClientConfig {
    @Bean
    public RestClient kakaoRestClient(KakaoProperties props, RestClient.Builder builder) {
        return builder.baseUrl(props.getApi().getBaseurl() + props.getApi().getPath().getBook())
                .defaultHeader("Authorization", props.getRestapi().getKey())
                .build();
    }
}