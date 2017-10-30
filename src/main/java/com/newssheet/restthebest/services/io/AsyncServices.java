package com.newssheet.restthebest.services.io;

import java.util.concurrent.Future;

public interface AsyncServices {
    Future<Void> getArticles();
}
