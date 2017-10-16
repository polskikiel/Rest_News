package com.newssheet.restthebest.repo;

import com.newssheet.restthebest.model.News;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends CrudRepository<News, Long> {
}
