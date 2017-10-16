package com.newssheet.restthebest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
public class Article {
    @Id
    @GeneratedValue
    @JsonIgnore
    Long id;
    String author;
    String title;
    String description;
    String url;
    String urlToImg;
    String publishedAt;
}
