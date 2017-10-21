package com.newssheet.restthebest.repo;

import com.newssheet.restthebest.model.Author;
import com.newssheet.restthebest.model.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends CrudRepository<Author, String >{
    Author findByName(String name);

    List<Author> findAllByNews(News news);

    List<Author> findTop30ByOrderByArticlesNrDesc();

    List<Author> findTop30ByOrderByLikesDesc();
}
