package com.newssheet.restthebest.services;

import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.util.CompanyList;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AsyncServicesImpl implements AsyncServices {
    NewsServicesImpl newsServices;
    CompanyList companyList;
    RestServices restServices;

    @Async
    public void getArticles() {
        List<News> news = new ArrayList<>();

        companyList.getCompanies().forEach(
                s -> {
                    news.add(restServices.getArticlesFromCompany(s));
                });

        newsServices.saveNews(news);
    }

    @Async
    public void getLanguageLabelsForArticles() {

        System.out.println("LANG");

        List<News> allNews = newsServices.getAllNews();
        allNews.forEach(news -> {
            news.getArticles().stream().filter(article ->
                article.getLanguage().isEmpty() || article.getLanguage() == null
            ).forEach(article -> {
                article.setLanguage(restServices.getLanguageToArticle(article.getTitle()));
            });
        });

        newsServices.saveNews(allNews);
    }


}
