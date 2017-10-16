package com.newssheet.restthebest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ResultDto {
    public String language;
    public boolean isReliable;
    public double confidence;
}
