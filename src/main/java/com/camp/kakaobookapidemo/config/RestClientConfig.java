package com.camp.kakaobookapidemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
@EnableConfigurationProperties(KakaoProperties.class)
public class RestClientConfig {

    private final Environment env;

    public RestClientConfig(Environment env) {
        this.env = env;
    }

    public String getKey() {
        return env.getProperty("kakao.restapi.key");
    }

    @Bean
    public RestClient kakaoRestClient(KakaoProperties props, RestClient.Builder builder) {
        log.info("[DEBUG] Kakao API Key = {}", getKey());
        return builder.baseUrl(props.getApi().getBaseurl() + props.getApi().getPath().getBook())
                .defaultHeader("Authorization", getKey())
                .build();
    }
}