package com.newssheet.restthebest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ArticleDto {
    int likes;

    String author;
    String company;

    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;
}
