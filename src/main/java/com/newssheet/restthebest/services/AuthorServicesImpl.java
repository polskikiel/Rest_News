package com.newssheet.restthebest.services;

import com.newssheet.restthebest.dto.AuthorDto;
import com.newssheet.restthebest.model.Author;
import com.newssheet.restthebest.model.News;
import com.newssheet.restthebest.repo.ArticleRepo;
import com.newssheet.restthebest.repo.AuthorRepo;
import com.newssheet.restthebest.services.io.AuthorServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServicesImpl implements AuthorServices {
    AuthorRepo authorRepo;
    ArticleRepo articleRepo;
    NewsServicesImpl newsServices;

    private Author getFromBuilder(String name, News news) {
        return Author.builder().name(name).news(news).articlesNr(1).build();
    }

    @Override
    public Author getByName(String name) {
        return authorRepo.findByName(name);
    }

    @Override
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepo.findAll();
    }

    @Override
    public boolean isThisAuthorExist(String name) {
        if (this.getByName(name) == null) {
            return false;
        }
        return true;
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepo.save(author);
    }

    @Override
    public void saveAuthor(List<Author> author) {
        authorRepo.save(author);
    }


    @Override
    public void createAuthor(String name, News news) {
        this.saveAuthor(this.getFromBuilder(name, news));
    }


    @Override
    public void addArticle(String name, Long id) {
        Author author = this.getByName(name);

        /*List<Long> list = Arrays.asList(author.getArticlesId());
        list.add(id);

        author.setArticlesId((Long[])list.toArray());*/
        author.setArticlesNr(author.getArticlesNr() + 1);

        this.saveAuthor(author);
    }

    public List<AuthorDto> jsonAuthors(List<Author> authors) {
        List<AuthorDto> authorDtos = new ArrayList<>();
        authors.stream().forEach(author -> authorDtos.add(jsonAuthor(author)));
        return authorDtos;
    }

    private AuthorDto jsonAuthor(Author author) {
        return AuthorDto.builder().company(author.getNews().getCompany()).
                name(author.getName()).articlesNr(author.getArticlesNr()).
                likes(author.getLikes()).articles(newsServices.jsonArticles(articleRepo.getAllByAuthor(author.getName())))
                .build();
    }

    public List<Author> topAuthors() {
        return authorRepo.findTop30ByOrderByLikesDesc();
    }

    public List<Author> authorsWithMostArticles() {
        return authorRepo.findTop30ByOrderByArticlesNrDesc();
    }

    public AuthorDto getAuthor(String name) {
        try {
            return jsonAuthor(getByName(name));

        } catch (NullPointerException npe) {
            return null;
        }
    }

    private List<Author> getAuthorsFromCompany(String company) {
        return authorRepo.findAllByNews(newsServices.getCompanyWithArticles(company));
    }

    public List<AuthorDto> getAuthors(String company) {
        return jsonAuthors(getAuthorsFromCompany(company));
    }
}
