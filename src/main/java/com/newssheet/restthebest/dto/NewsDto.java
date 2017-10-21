package com.newssheet.restthebest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NewsDto {
    String company;
    String name;
    int likes;
    String language;
    String category;

    String description;

    List<ArticleDto> articles;
}
