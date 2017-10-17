package com.newssheet.restthebest.repo;

import com.newssheet.restthebest.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends CrudRepository<Article, Long> {
    List<Article> getAllByAuthor(String author);

    List<Article> getTop30ByOrderByLikesDesc();
}
