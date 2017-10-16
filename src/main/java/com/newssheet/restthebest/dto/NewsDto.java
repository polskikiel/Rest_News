package com.newssheet.restthebest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newssheet.restthebest.model.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsDto implements Serializable {
    String source;
    String sortBy;
    String status;
    List<Article> articles;
}
