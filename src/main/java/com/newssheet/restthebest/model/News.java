package com.newssheet.restthebest.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue
    Long id;
    String company;

    @OneToMany(mappedBy = "news")
    List<Article> articles;

}
