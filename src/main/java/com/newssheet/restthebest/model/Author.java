package com.newssheet.restthebest.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Author {
    @Id
    @Column(length = 1000)
    String name;

    @ManyToOne
    News news;

    int likes;
    int articlesNr;


}
