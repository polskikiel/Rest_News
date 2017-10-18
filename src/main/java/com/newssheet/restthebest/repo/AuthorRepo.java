package com.newssheet.restthebest.repo;

import com.newssheet.restthebest.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends CrudRepository<Author, String>{
    List<Author> findAllByCompany(String company);
}
