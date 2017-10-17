package com.newssheet.restthebest.dto;

import com.newssheet.restthebest.model.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDto {
    String name;
    String company;
    int likes;
    int articlesNr;
    List<Article> articles;

}
