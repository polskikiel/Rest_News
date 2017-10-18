package com.newssheet.restthebest.services.io;

import com.newssheet.restthebest.model.News;

import java.util.List;

public interface RestServices {
    List<News> getArticles();
}
