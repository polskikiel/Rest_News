package com.newssheet.restthebest.util;

import com.newssheet.restthebest.dto.SourceResponseDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Sources {
    @Autowired
    private RestTemplate restTemplate;

    @Getter
    private SourceResponseDto sources;

    public SourceResponseDto build() {
        return sources = restTemplate.exchange("https://newsapi.org/v1/sources?apiKey=325f88816211470b89e1c5e430fa45d6",
                HttpMethod.GET, HttpEntity.EMPTY, SourceResponseDto.class).getBody();
    }

}
