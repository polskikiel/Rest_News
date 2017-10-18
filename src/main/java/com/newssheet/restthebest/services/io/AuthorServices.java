package com.newssheet.restthebest.services.io;

import com.newssheet.restthebest.model.Article;
import com.newssheet.restthebest.model.Author;

import java.util.List;

public interface AuthorServices {
    Author getByName(String name);

    List<Author> getAllAuthorsFromCompany(String company);

    List<Author> getAllAuthors();

    boolean isThisAuthorExist(String name);

    void saveAuthor(Author author);

    void saveAuthor(List<Author> author);

    void addArticleToAuthor(Author author, Article article);

    void addArticleToAuthor(String author, Article article);

    void createAuthor(String name, String company);

}
