package com.newssheet.restthebest.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    String company;
    String name;
    int likes;
    String language;
    String category;

    @Column(length = 100001)
    String description;

    @OneToMany(mappedBy = "news")
    List<Article> articles;

}
