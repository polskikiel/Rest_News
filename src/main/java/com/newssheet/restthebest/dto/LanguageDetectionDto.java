package com.newssheet.restthebest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LanguageDetectionDto {
    List<ResultDto> resultDtos;
}
