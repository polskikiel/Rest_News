package com.newssheet.restthebest.services;


import com.newssheet.restthebest.dto.ImgDto;
import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.repo.ArticleRepo;
import com.newssheet.restthebest.services.io.AuthorServices;
import com.newssheet.restthebest.services.io.RestServices;
import com.newssheet.restthebest.util.Sources;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    NewsServicesImpl newsServices;

    @Getter
    private final String newsApiKey = "325f88816211470b89e1c5e430fa45d6";

    public List<News> getArticles() {
        List<News> newsList = new ArrayList<>();
        sources.build().getSources().forEach(sourceDto -> {

            String url = "https://newsapi.org/v1/articles?apiKey=" + newsApiKey + "&source=" + sourceDto.getId();

            News n = restTemplate.
                    exchange(url, HttpMethod.GET, HttpEntity.EMPTY, News.class).getBody();

            if (sources.isHaveChanged()) {
                ImgDto imgDto = restTemplate.exchange("https://api.qwant.com/api/search/images?count=3&offset=1&q=" + sourceDto.getName(),
                        HttpMethod.GET, HttpEntity.EMPTY, ImgDto.class).getBody();
                System.out.println("have");
                n.setImg(imgDto.getData().getResult().getItems()[0].getMedia());
            }

            n.setCompany(sourceDto.getId());
            n.setCategory(sourceDto.getCategory());
            n.setLanguage(sourceDto.getLanguage());
            n.setName(sourceDto.getName());
            n.setDescription(sourceDto.getDescription());


            newsServices.saveNews(n);
            n.getArticles().
                    forEach(article -> {
                        if (this.isHaveBadChars(article.getDescription())) {
                            article.setDescription(this.removeBadChars(article.getDescription()));
                        }
                        if (this.isHaveBadChars(article.getTitle())) {
                            article.setTitle(this.removeBadChars(article.getTitle()));
                        }

                        if (!newsServices.isThisArticleExist(article)) {
                            article.setNews(n);
                            if (article.getAuthor() != null && !article.getAuthor().isEmpty()) {            // CREATE ANONYMOUS WITH THIS COMPANY ( LONG ID )
                                if (!authorServices.isThisAuthorExist(article.getAuthor())) {
                                    authorServices.createAuthor(article.getAuthor(), n);
                                } else {
                                    authorServices.addArticle(article.getAuthor(), article.getId());
                                }
                            }
                            articleRepo.save(article);
                        }
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


