package com.newssheet.restthebest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author {
    @Id
    String name;
    String company;
    int likes;
    int articlesNr;

    @OneToMany
    List<Article> articles;
}
