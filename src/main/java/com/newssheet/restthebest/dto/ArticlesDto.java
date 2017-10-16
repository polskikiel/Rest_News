package com.newssheet.restthebest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticlesDto {
    String author;
    String title;
    String description;
    String url;
    String urlToImg;
    String publishedAt;
}
