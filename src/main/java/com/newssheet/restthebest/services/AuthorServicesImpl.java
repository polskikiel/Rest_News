package com.newssheet.restthebest.services;

import com.newssheet.restthebest.model.Author;
import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.repo.AuthorRepo;
import com.newssheet.restthebest.services.io.AuthorServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void createAuthor(String name, News news) {
        this.saveAuthor(Author.builder().name(name).news(news).articlesNr(1).build());
    }

    @Override
    public void addArticle(String name) {
        Author author = this.getByName(name);
        author.setArticlesNr(author.getArticlesNr() + 1);
        this.saveAuthor(author);
    }
}
