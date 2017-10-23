package com.newssheet.restthebest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder                // can't use builder (?)
@Getter
@Setter
@AllArgsConstructor
public class CompaniesDto {
    String company;
    String name;
    int likes;
    String language;
    String category;
    String imgUrl;
}
