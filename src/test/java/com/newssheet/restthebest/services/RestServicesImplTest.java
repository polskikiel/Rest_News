package com.newssheet.restthebest.services;

import com.newssheet.restthebest.dto.ImgDto;
import com.newssheet.restthebest.dto.SourceResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestServicesImplTest {
    TestRestTemplate restTemplate;

    @Autowired
    RestServicesImpl restServices;

    @Before
    public void initRestTemplate() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void testSources() {
        ResponseEntity<SourceResponseDto> sources = restTemplate.exchange("https://newsapi.org/v1/sources?apiKey=" + restServices.getNewsApiKey(),
                HttpMethod.GET, HttpEntity.EMPTY, SourceResponseDto.class);
        Assert.assertTrue(sources.getStatusCode().is2xxSuccessful());
        Assert.assertTrue("STATUS NOT OK[200]", sources.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void testImages() {
        ResponseEntity<ImgDto> images = restTemplate.getForEntity("https://api.qwant.com/api/search/images?count=3&offset=1&q=img", ImgDto.class);
        Assert.assertTrue(images.getStatusCode().is2xxSuccessful());
        Assert.assertTrue(images.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void test() {
        ResponseEntity<SourceResponseDto> sources = restTemplate.exchange("https://newsapi.org/v1/sources?apiKey=" + restServices.getNewsApiKey(),
                HttpMethod.GET, HttpEntity.EMPTY, SourceResponseDto.class);
        Assert.assertTrue(sources.getStatusCode().is2xxSuccessful());
        Assert.assertTrue("STATUS NOT OK[200]", sources.getStatusCode().equals(HttpStatus.OK));

        sources.getBody().getSources().stream().
                forEach(sourceDto -> {
                    ResponseEntity<SourceResponseDto> article = restTemplate.exchange("https://newsapi.org/v1/articles?apiKey=" + restServices.getNewsApiKey() + "&source=" + sourceDto.getId(),
                            HttpMethod.GET, HttpEntity.EMPTY, SourceResponseDto.class);
                    Assert.assertTrue(article.getStatusCode().is2xxSuccessful());
                    Assert.assertTrue("STATUS NOT OK[200]", article.getStatusCode().equals(HttpStatus.OK));
                });

    }
}