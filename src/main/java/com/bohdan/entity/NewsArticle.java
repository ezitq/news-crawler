package com.bohdan.entity;

public record NewsArticle(
        String title,
        String description,
        String content,
        String url,
        String image,
        String publishedAt,
        NewsSource source
) {
}