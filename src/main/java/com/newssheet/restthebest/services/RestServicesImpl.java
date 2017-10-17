package com.newssheet.restthebest.services;


import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.util.Sources;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
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
    Sources sources;

    private final String newsApiKey = "325f88816211470b89e1c5e430fa45d6";

    public List<News> getArticles() {
        List<News> newsList = new ArrayList<>();
        sources.getSources().getSources().forEach(sourceDto -> {
            String url = "https://newsapi.org/v1/articles?apiKey=" + newsApiKey + "&source=" + sourceDto.getId();

            ResponseEntity<News> news =
                    restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, News.class);

            News n = news.getBody();
            n.setCompany(sourceDto.getId());
            n.setCategory(sourceDto.getCategory());
            n.setLanguage(sourceDto.getLanguage());
            n.setName(sourceDto.getName());
            n.setDescription(sourceDto.getDescription());
            newsList.add(n);
        });
        return newsList;
    }


}


