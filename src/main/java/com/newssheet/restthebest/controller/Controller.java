package com.newssheet.restthebest.controller;

import com.newssheet.restthebest.dto.CompaniesDto;
import com.newssheet.restthebest.model.Article;
import com.newssheet.restthebest.model.News;
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

    @GetMapping("/company/{company}")
    public News getNewsFromCompany(@PathVariable("company") final String company) {
        return newsServices.getCompanyWithArticles(company);
    }

    @GetMapping("/company/{company}/article")
    public List<Article> getArticlesFromCompany(@PathVariable("company") final String company) {
        return newsServices.getCompanyWithArticles(company).getArticles();
    }

    @GetMapping("/company/{company}/article/{id}")
    public Article getNewsFromCompanyId(@PathVariable("company") final String company,
                                        @PathVariable("id") final Integer id) {
        return newsServices.getCompanyWithArticles(company).getArticles().get(id);
    }

    @GetMapping("/author/{author}")
    public List<Article> getNewsFromAuthor(@PathVariable("author") final String author) {
        return newsServices.getArticlesFromAuthor(author);
    }

    @GetMapping("/articles/top")
    public List<Article> bestArticles() {
        return newsServices.getTop30Articles();
    }

    @GetMapping("/company")
    public List<CompaniesDto> getAllCompanies() {
        return newsServices.getAllCompanies();
    }

    @GetMapping("/news")
    public List<News> getNewsByLanguageOrCategory(@RequestParam(value = "lang", required = false) final String lang,
                                                  @RequestParam(value = "cat", required = false) final String[] cat) {
        if (lang != null && cat != null) {
            return newsServices.getNewsByCategories(cat, newsServices.getArticlesByLanguage(lang));
        } else if (lang == null) {
            return newsServices.getNewsByCategories(cat, newsServices.getAllNews());
        } else if (cat == null) {
            return newsServices.getArticlesByLanguage(lang);
        }
        return null;        // we can make return newsServices.getAll(), but it would crash server really easy
    }

}
