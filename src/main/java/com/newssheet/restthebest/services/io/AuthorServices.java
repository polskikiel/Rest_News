package com.newssheet.restthebest.services.io;

import com.newssheet.restthebest.model.Author;
import com.newssheet.restthebest.model.News;

import java.util.List;

public interface AuthorServices {
    Author getByName(String name);

    List<Author> getAllAuthors();

    boolean isThisAuthorExist(String name);

    void saveAuthor(Author author);

    void saveAuthor(List<Author> author);

    void createAuthor(String name, News news);

    void addArticle(String name);
}
