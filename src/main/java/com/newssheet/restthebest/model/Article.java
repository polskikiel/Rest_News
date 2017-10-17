package com.newssheet.restthebest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    @Id
    @GeneratedValue
    @JsonIgnore
    Long id;

    @JsonIgnore
    int likes;

    @JsonIgnore
    @ManyToOne
    News news;


    @Column(length = 1000)
    String author;
    @Column(length = 1000)
    String title;

    @Column(length = 10001)
    String description;

    @Column(length = 1000)
    String url;
    @Column(length = 1000)
    String urlToImage;
    @Column(length = 1000)
    String publishedAt;
}
