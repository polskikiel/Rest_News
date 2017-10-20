package com.newssheet.restthebest.repo;

import com.newssheet.restthebest.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends CrudRepository<Author, String>{
}
