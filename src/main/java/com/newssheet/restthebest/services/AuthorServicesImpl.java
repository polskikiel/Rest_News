package com.newssheet.restthebest.services;

import com.newssheet.restthebest.model.Article;
import com.newssheet.restthebest.model.Author;
import com.newssheet.restthebest.repo.AuthorRepo;
import com.newssheet.restthebest.services.io.AuthorServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServicesImpl implements AuthorServices {
    AuthorRepo authorRepo;

    @Override
    public Author getByName(String name) {
        return authorRepo.findOne(name);
    }

    @Override
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepo.findAll();
    }

    @Override
    public List<Author> getAllAuthorsFromCompany(String company) {
        return authorRepo.findAllByCompany(company);
    }

    @Override
    public boolean isThisAuthorExist(String name) {
        if (this.getByName(name) == null) {
            return false;
        }
        return true;
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepo.save(author);
    }

    @Override
    public void saveAuthor(List<Author> author) {
        authorRepo.save(author);
    }
    @Override
    public void addArticleToAuthor(String author, Article article) {
        Author author1 = this.getByName(author);
        List<Article> articles = author1.getArticles();
        articles.add(article);
        author1.setArticles(articles);
        author1.setArticlesNr(author1.getArticlesNr() + 1);

        this.saveAuthor(author1);
    }
    @Override
    public void addArticleToAuthor(Author author, Article article) {
        List<Article> articles = author.getArticles();
        articles.add(article);
        author.setArticles(articles);
        author.setArticlesNr(author.getArticlesNr() + 1);

        this.saveAuthor(author);
    }
    @Override
    public void createAuthor(String name, String company) {
        this.saveAuthor(Author.builder().name(name).company(company).articlesNr(1).articles(new ArrayList<>()).build());
    }
}
