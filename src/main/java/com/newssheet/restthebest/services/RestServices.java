package com.newssheet.restthebest.services;

import com.newssheet.restthebest.model.News;

import java.util.List;

public interface RestServices {
    News getArticlesFromCompany(String company);

    List<News> getArticles();
}
