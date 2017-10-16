package com.newssheet.restthebest.repo;

import com.newssheet.restthebest.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends CrudRepository<Article, Long> {
}
