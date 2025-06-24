package com.camp.kakaobookapidemo.service;

import com.camp.kakaobookapidemo.dto.KakaoBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class KakaoBookSearchService {
    private final RestClient kakaoBookRestClient;

    public KakaoBookResponse searchBooks(String query, int size) {
        return kakaoBookRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", query)
                        .queryParam("size", size)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(KakaoBookResponse.class);
    }
}