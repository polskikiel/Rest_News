package com.newssheet.restthebest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class News {
    @Id
    @GeneratedValue
    Long id;
    Integer likes;
    String company;

    @OneToMany
    List<Article> articles;


}
