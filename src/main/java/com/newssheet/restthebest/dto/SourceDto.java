package com.newssheet.restthebest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SourceDto {

    String id;
    String name;
    String description;
    String url;
    String category;
    String language;
    String country;

}
