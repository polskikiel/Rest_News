package com.newssheet.restthebest.services;


import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.repo.ArticleRepo;
import com.newssheet.restthebest.services.io.AuthorServices;
import com.newssheet.restthebest.services.io.RestServices;
import com.newssheet.restthebest.util.Sources;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    AuthorServices authorServices;
    ArticleRepo articleRepo;

    @Getter
    private final String newsApiKey = "325f88816211470b89e1c5e430fa45d6";

    public List<News> getArticles() {
        List<News> newsList = new ArrayList<>();
        sources.build().getSources().forEach(sourceDto -> {

            String url = "https://newsapi.org/v1/articles?apiKey=" + newsApiKey + "&source=" + sourceDto.getId();

            ResponseEntity<News> news =
                    restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, News.class);

            News n = news.getBody();

            n.setCompany(sourceDto.getId());
            n.setCategory(sourceDto.getCategory());
            n.setLanguage(sourceDto.getLanguage());
            n.setName(sourceDto.getName());
            n.setDescription(sourceDto.getDescription());



            n.getArticles().
                    forEach(article -> {
                        if (this.isHaveBadChars(article.getDescription())) {
                            article.setDescription(this.removeBadChars(article.getDescription()));
                        }
                        if (this.isHaveBadChars(article.getTitle())) {
                            article.setTitle(this.removeBadChars(article.getTitle()));
                        }

                        article.setNews(n);

                        if (article.getAuthor() != null) {
                            if (!authorServices.isThisAuthorExist(article.getAuthor())) {
                                authorServices.createAuthor(article.getAuthor(), n);
                            }
                        }

                        articleRepo.save(article);
                    });

            newsList.add(n);

        });
        return newsList;
    }

    private String removeBadChars(final String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isHighSurrogate(s.charAt(i))) continue;
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    private boolean isHaveBadChars(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (Character.isHighSurrogate(s.charAt(i))) return true;
        }
        return false;
    }

}


