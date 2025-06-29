package com.camp.kakaobookapidemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KakaoBookResponse(
        List<Document> documents
) {
    public record Document(
            String title,
            List<String> authors,
            String publisher,
            String thumbnail,
            String contents
    ) {}
}