package com.camp.kakaobookapidemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
@EnableConfigurationProperties(KakaoProperties.class)
public class RestClientConfig {
    @Bean
    public RestClient kakaoRestClient(KakaoProperties props, RestClient.Builder builder) {
        log.info("[DEBUG] Kakao API Key = {}", props.getRestapi().getKey());
        return builder.baseUrl(props.getApi().getBaseurl() + props.getApi().getPath().getBook())
                .defaultHeader("Authorization", props.getRestapi().getKey())
                .build();
    }
}