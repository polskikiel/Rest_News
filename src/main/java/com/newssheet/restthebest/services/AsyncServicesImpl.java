package com.newssheet.restthebest.services;

import com.newssheet.restthebest.services.io.AsyncServices;
import com.newssheet.restthebest.services.io.RestServices;
import lombok.AllArgsConstructor;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.concurrent.Future;

@Service
@Transactional
@AllArgsConstructor
public class AsyncServicesImpl implements AsyncServices {
    NewsServicesImpl newsServices;
    RestServices restServices;

    @Async
    public Future<Void> getArticles() throws GenericJDBCException {
        newsServices.saveNews(restServices.getArticles());

        System.out.println("getArticles refreshed at - " + Calendar.getInstance().getTime().toString());
        return new AsyncResult<>(null);
    }

}
