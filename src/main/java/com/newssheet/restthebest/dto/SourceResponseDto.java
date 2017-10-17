package com.newssheet.restthebest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SourceResponseDto {
    String status;
    List<SourceDto> sources;
}
