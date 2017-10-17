package com.newssheet.restthebest.repo;

import com.newssheet.restthebest.model.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepo extends CrudRepository<News, String> {
    List<News> getAllByLanguage(String language);
}
