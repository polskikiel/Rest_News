package com.newssheet.restthebest.services;

import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.repo.NewsRepo;
import com.newssheet.restthebest.util.CompanyList;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AsyncServicesImpl implements AsyncServices {
    NewsRepo newsRepo;
    CompanyList companyList;
   // RestServices restServices;

    @Async
    public void getArticles() {
        List<News> news = new ArrayList<>();
        System.out.println("thread");
        for (String company : companyList.getCompanies()) {
           // news.add(restServices.getArticlesFromCompany(company));
        }
    }


}
