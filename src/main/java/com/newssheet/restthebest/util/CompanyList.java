package com.newssheet.restthebest.util;

import com.sun.javafx.collections.ImmutableObservableList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
@Setter
public class CompanyList {
    private List<String> companies;

    @PostConstruct
    public void init() {
        companies = Arrays.asList(new String[]{"abc-news-au", "al-jazeera-english", "ars-technica","associated-press",
        "bbc-news", "bbc-sport", "bild", "bloomberg", "breitbart-news", "business-insider", "business-insider-uk", "buzzfeed", "cnbc",
        "cnn", "daily-mail", "der-tagesspiegel", "die-zeit", "engadget", "entertainment-weekly", "espn", "espn-cric-info", "financial-times",
        "focus", "football-italia", "fortune", "four-four-two", "fox-sports", "google-news", "gruenderszene", "hacker-news", "handelsblatt",
        "ign", "independent", "mashable", "metro", "mirror", "mtv-news", "mtv-news-uk", "national-geographic", "new-scientist", "newsweek",
        "new-york-magazine", "nfl-news", "polygon", "recode", "reddit-r-all", "reuters", "spiegel-online", "t3n", "talksport", "techcrunch", "techradar",
        "the-economist", "the-guardian-au", "the-guardian-uk", "the-hindu", "the-huffington-post", "the-lad-bible", "the-new-york-times", "the-next-web",
        "the-sport-bible", "the-telegraph", "the-times-of-india", "the-verge", "the-wall-street-journal", "the-washington-post", "time", "usa-today",
        "wired-de", "wirtschafts-woche"});

    }
}
