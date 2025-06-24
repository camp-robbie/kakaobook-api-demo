package com.camp.kakaobookapidemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
@EnableConfigurationProperties(KakaoProperties.class)
public class RestClientConfig {

    @Value("${kakao.restapi.key}")
    private String kakaoKey;

    @Bean
    public RestClient kakaoRestClient(KakaoProperties props, RestClient.Builder builder) {
        log.info("[DEBUG] Kakao API Key = {}", kakaoKey);
        return builder.baseUrl(props.getApi().getBaseurl() + props.getApi().getPath().getBook())
                .defaultHeader("Authorization", kakaoKey)
                .build();
    }
}