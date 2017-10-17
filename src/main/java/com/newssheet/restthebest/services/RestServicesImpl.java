package com.newssheet.restthebest.services;


import com.newssheet.restthebest.dto.SourceResponseDto;
import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.util.CompanyList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class RestServicesImpl implements RestServices {
    RestTemplate restTemplate;
    @Getter
    CompanyList companyList;

    private final String newsApiKey = "325f88816211470b89e1c5e430fa45d6";

    @Override
    public News getArticlesFromCompany(String company) {
        if (companyList.getCompanies().contains(company)) {
            String url = "https://newsapi.org/v1/articles?apiKey=" + newsApiKey + "&source=" + company;

            ResponseEntity<SourceResponseDto> sources =
                    restTemplate.exchange("https://newsapi.org/v1/sources?apiKey=325f88816211470b89e1c5e430fa45d6", HttpMethod.GET, HttpEntity.EMPTY, SourceResponseDto.class);
            ResponseEntity<News> news =
                    restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, News.class);

            News n = news.getBody();
            n.setCompany(company);

            sources.getBody().getSources().stream().
                    filter(sourceDto -> sourceDto.getId().equals(n.getCompany())).
                    forEach(sourceDto -> {

                n.setLanguage(sourceDto.getLanguage());
                n.setName(sourceDto.getName());
            });

            return n;
        } else {
            return null;
        }
    }



}


