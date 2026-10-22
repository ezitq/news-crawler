package com.bohdan.entity;

public record NewsRequest(
        String language,
        String country,
        String topic,
        Integer maxArticles
) {
}
