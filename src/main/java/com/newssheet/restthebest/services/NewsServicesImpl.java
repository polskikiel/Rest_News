package com.newssheet.restthebest.services;

import com.newssheet.restthebest.dto.CompaniesDto;
import com.newssheet.restthebest.model.Article;
import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.repo.ArticleRepo;
import com.newssheet.restthebest.repo.NewsRepo;
import com.newssheet.restthebest.services.io.NewsServices;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsServicesImpl implements NewsServices {
    private NewsRepo newsRepo;
    private ArticleRepo articleRepo;

    public void saveNews(@NonNull News news) {
        newsRepo.save(news);
    }

    public void saveNews(@NonNull final List<News> news) {
        newsRepo.save(news);
    }

    public News getCompanyWithArticles(String company) {
        return newsRepo.findOne(company);
    }

    public List<Article> getArticlesFromAuthor(String author) {
        return articleRepo.getAllByAuthor(author);
    }

    public List<News> getArticlesByLanguage(String language) {
        return newsRepo.getAllByLanguage(language);
    }

    public List<Article> getTop30Articles() {
        return articleRepo.getTop30ByOrderByLikesDesc();
    }

    public List<News> getAllNews() {
        return (List<News>) newsRepo.findAll();
    }

    public List<CompaniesDto> getAllCompanies() {
        List<CompaniesDto> companiesDto = new ArrayList<>();
        this.getAllNews().forEach(news -> companiesDto.add(
                new CompaniesDto(news.getCompany(), news.getName(), news.getLikes(), news.getLanguage(), news.getCategory())));
        return companiesDto;
    }

    public List<News> getNewsByCategories(String[] categories, List<News> n) {
        return n.stream().
                filter(news -> Arrays.asList(categories).contains(news.getCategory())).
                collect(Collectors.toList());
    }

    public List<News> getNewsByLanguage(String language) {
        return newsRepo.getAllByLanguage(language);
    }
}
