package com.newssheet.restthebest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article implements Serializable {
    @Id
    @GeneratedValue
    Long id;
    int likes;


    @Column(length = 1000)
    String author;

    @ManyToOne
    News news;

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
