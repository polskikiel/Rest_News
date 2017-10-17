package com.newssheet.restthebest.services;

import com.newssheet.restthebest.util.CompanyList;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AsyncServicesImpl implements AsyncServices {
    NewsServicesImpl newsServices;
    CompanyList companyList;
    RestServices restServices;

    @Async
    public void getArticles() {

        newsServices.saveNews(restServices.getArticles());
        System.out.println("getArticles refreshed");
    }



}
