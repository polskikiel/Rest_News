package com.newssheet.restthebest.services;

import com.newssheet.restthebest.dto.NewsDto;
import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.util.CompanyList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RestServicesImpl implements RestServices {
    RestTemplate restTemplate;
    @Getter
    CompanyList companyList;

    @Override
    public News getArticlesFromCompany(String company) {
        if (companyList.getCompanies().contains(company)) {
            String url = "https://newsapi.org/v1/articles?apiKey=325f88816211470b89e1c5e430fa45d6&source=" + company;
            ResponseEntity<News> news = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, News.class);

            return news.getBody();
        } else {
            return null;
        }
    }

}


