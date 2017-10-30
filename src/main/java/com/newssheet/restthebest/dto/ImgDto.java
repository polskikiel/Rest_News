package com.newssheet.restthebest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImgDto {
    Data data;
    String status;

    @lombok.Data
    @AllArgsConstructor
    public static class Data {
        Result result;

        @lombok.Data
        @AllArgsConstructor
        public static class Result {
            Items[] items;

            @lombok.Data            //@Data contains whole set of utilities for typical POJO objects
            @AllArgsConstructor
            public static class Items {
                String media;
                String url;
                String media_fullsize;
            }
    }


    }
}
