package com.newssheet.restthebest.controller;

import com.newssheet.restthebest.dto.ArticleDto;
import com.newssheet.restthebest.dto.AuthorDto;
import com.newssheet.restthebest.dto.CompaniesDto;
import com.newssheet.restthebest.dto.NewsDto;
import com.newssheet.restthebest.services.AuthorServicesImpl;
import com.newssheet.restthebest.services.NewsServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {
    NewsServicesImpl newsServices;
    AuthorServicesImpl authorServices;

    @GetMapping("/company/{company}")
    public NewsDto getNewsFromCompany(@PathVariable("company") final String company) {
        return newsServices.jsonNews(newsServices.getCompanyWithArticles(company));
    }

    @GetMapping("/company/{company}/article/{id}")
    public ArticleDto getNewsFromCompanyId(@PathVariable("company") final String company,
                                        @PathVariable("id") final Integer id) {
        return newsServices.jsonArticles(newsServices.getCompanyWithArticles(company).getArticles().get(id));
    }

    @GetMapping("/author/{author}")
    public AuthorDto getNewsFromAuthor(@PathVariable("author") final String name) {
        return authorServices.getAuthor(name);
    }

    @GetMapping("/authors/top")
    public List<AuthorDto> getTopAuthors(@RequestParam("by") String by) {
        if (by.equals("likes")) {
            return authorServices.jsonAuthors(authorServices.topAuthors());
        } else if (by.equals("art")) {
            return authorServices.jsonAuthors(authorServices.authorsWithMostArticles());
        }
        return null;
    }

    @GetMapping("/company/{company}/authors")
    public List<AuthorDto> getAuthorsFromCompany(@PathVariable("company") final String company) {
        return authorServices.getAuthors(company);
    }

    @GetMapping("/articles/top")
    public List<ArticleDto> bestArticles() {
        return newsServices.jsonArticles(newsServices.getTop30Articles());
    }

    @GetMapping("/company")
    public List<CompaniesDto> getAllCompanies() {
        return newsServices.getAllCompanies();
    }

    @GetMapping("/news")
    public List<NewsDto> getNewsByLanguageOrCategory(@RequestParam(value = "lang", required = false) final String lang,
                                                  @RequestParam(value = "cat", required = false) final String[] cat) {
        if (lang != null && cat != null) {
            return newsServices.jsonNews(newsServices.getNewsByCategories(cat, newsServices.getArticlesByLanguage(lang)));
        } else if (lang == null) {
            return newsServices.jsonNews(newsServices.getNewsByCategories(cat, newsServices.getAllNews()));
        } else if (cat == null) {
            return newsServices.jsonNews(newsServices.getArticlesByLanguage(lang));
        }
        return null;        // we can make return newsServices.getAll(), but it would crash server really easy
    }

}
