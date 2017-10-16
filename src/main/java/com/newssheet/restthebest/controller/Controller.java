package com.newssheet.restthebest.controller;

import com.newssheet.restthebest.dto.NewsDto;
import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.services.RestServices;
import com.newssheet.restthebest.services.RestServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {
    RestServicesImpl restServices;

    @GetMapping("/")
    public String getSite() {
        return "main";
    }

    @GetMapping("/{company}")
    public News getNewsFromCompany(@PathVariable("company") String company) {
        return restServices.getArticlesFromCompany(company);
    }

    @GetMapping("/{company}/{id}")
    public NewsDto getNewsFromCompanyId(@PathVariable("company") String company,
                                        @PathVariable("id") Integer id) {
        return null;
    }
}
