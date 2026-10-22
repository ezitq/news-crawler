package com.bohdan.entity;

import java.util.List;

public record GNewsResponse(
        int totalArticles,
        List<NewsArticle> articles
) {
}