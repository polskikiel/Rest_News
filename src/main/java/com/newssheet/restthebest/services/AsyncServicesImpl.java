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
                    News n = restServices.getArticlesFromCompany(s);
                    news.add(n);
                });
        newsServices.saveNews(news);
        System.out.println("getArticles refreshed");
    }



}
