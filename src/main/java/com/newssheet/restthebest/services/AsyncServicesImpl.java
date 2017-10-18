package com.newssheet.restthebest.services;

import com.newssheet.restthebest.services.io.AsyncServices;
import com.newssheet.restthebest.services.io.RestServices;
import lombok.AllArgsConstructor;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@Service
@Transactional
@AllArgsConstructor
public class AsyncServicesImpl implements AsyncServices {
    NewsServicesImpl newsServices;
    RestServices restServices;

    @Async
    public void getArticles() throws GenericJDBCException {
        try {
            newsServices.saveNews(restServices.getArticles());
        } catch (GenericJDBCException gen) {
            throw new GenericJDBCException("Bad signs in description or title", gen.getSQLException());
        }
        System.out.println("getArticles refreshed at - " + Calendar.getInstance().getTime().toString());
    }



}
