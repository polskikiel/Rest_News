package com.newssheet.restthebest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    Integer likes;

    @JsonIgnore
    @ManyToOne
    News news;

    @JsonIgnore
    String language;

    @Column(length = 1000)
    String author;
    @Column(length = 1000)
    String title;

    @Column(length = 10000)
    String description;

    @Column(length = 1000)
    String url;
    @Column(length = 1000)
    String urlToImage;
    @Column(length = 1000)
    String publishedAt;
}
