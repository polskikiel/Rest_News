package com.newssheet.restthebest.services;

import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.repo.ArticleRepo;
import com.newssheet.restthebest.repo.NewsRepo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class NewsServicesImpl implements NewsServices {
    private NewsRepo newsRepo;
    private ArticleRepo articleRepo;

    public void saveNews(@NonNull News news) {
        newsRepo.save(news);
    }

    public void saveNews(@NonNull List<News> news) {
        news.forEach(news1 -> {
            news1.getArticles().forEach(article -> {
                article.setNews(news1);
            });
            articleRepo.save(news1.getArticles());
        });
        newsRepo.save(news);
    }

    public List<News> getAllNews() {
        return (List<News>)newsRepo.findAll();
    }

    public News getById(Long id) {
        return newsRepo.findOne(id);
    }

}
