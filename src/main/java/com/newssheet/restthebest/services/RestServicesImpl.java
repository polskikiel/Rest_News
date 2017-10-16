package com.newssheet.restthebest.services;

import com.newssheet.restthebest.dto.LanguageDetectionDto;
import com.newssheet.restthebest.dto.ResultDto;
import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.util.CompanyList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
            news.getBody().setCompany(company);
            return news.getBody();
        } else {
            return null;
        }
    }

    public String getLanguageToArticle(String text) {
        String url = "http://ws.detectlanguage.com/0.2/detect?key=7a4f574fc3786fc9670c0a614fec04b6&q=" + text;

        ResponseEntity<LanguageDetectionDto> dtoResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, LanguageDetectionDto.class);

        System.out.println(dtoResponseEntity.getBody().getResultDtos().get(0).getLanguage());

        return dtoResponseEntity.getBody().getResultDtos().stream().filter(ResultDto::isReliable).findFirst().get().getLanguage();
    }

}


