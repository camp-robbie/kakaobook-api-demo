package com.camp.kakaobookapidemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperties {
    private Restapi restapi;
    private Api api;

    @Data
    public static class Restapi {
        private String key;
    }

    @Data
    public static class Api {
        private String baseurl;
        private Path path;

        @Data
        public static class Path {
            private String book;
        }
    }
}
