package com.newssheet.restthebest.services;

import com.newssheet.restthebest.dto.CompaniesDto;
import com.newssheet.restthebest.model.Article;
import com.newssheet.restthebest.model.Author;
import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.repo.ArticleRepo;
import com.newssheet.restthebest.repo.AuthorRepo;
import com.newssheet.restthebest.repo.NewsRepo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsServicesImpl implements NewsServices {
    private NewsRepo newsRepo;
    private ArticleRepo articleRepo;
    private AuthorRepo authorRepo;

    public void saveNews(@NonNull News news) {
        newsRepo.save(news);
    }
    public void saveNews(@NonNull List<News> news) {
        newsRepo.save(news);
        news.forEach(news1 -> {
            news1.getArticles().forEach(article -> article.setNews(news1));
            articleRepo.save(news1.getArticles());
        });
        newsRepo.save(news);
    }

    public void saveAuthor(Author author) {
        authorRepo.save(author);
    }
    public void saveAuthor(List<Author> author) {
        authorRepo.save(author);
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
        return (List<News>)newsRepo.findAll();
    }

    public List<CompaniesDto> getAllCompanies() {
        List<CompaniesDto> companiesDto = new ArrayList<>();
        this.getAllNews().forEach(news -> companiesDto.add(
                new CompaniesDto(news.getCompany(), news.getName(), news.getLikes(), news.getLanguage())));
        return companiesDto;
    }

    public List<Author> getAllAuthors() {
        return (List<Author>)authorRepo.findAll();
    }

    public Author getAuthorByName(String name) {
        return authorRepo.findOne(name);
    }


}
