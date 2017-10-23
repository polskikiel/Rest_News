package com.newssheet.restthebest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImgDto {
    Data data;
    String status;

    @lombok.Data
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Data {
        Result result;

        @lombok.Data
        @Getter
        @Setter
        @AllArgsConstructor
        public static class Result {
            Items[] items;

            @lombok.Data
            @Getter
            @Setter
            @AllArgsConstructor
            public static class Items {
                String media;
                String url;
                String media_fullsize;
            }
    }


    }
}
