package com.ua.verdysh.controller.parsers;

import org.jsoup.Jsoup;

import java.util.List;

public class EmeritusPageParser{

    private static final String FULL_NAME_SELECTOR = "h4:nth-child(2)";
    private static final String URL_SELECTOR = "ul.small-block-grid-2.medium-block-grid-4.large-block-grid-5 a";

    private final String html;

    public EmeritusPageParser(String html) {
        this.html = html;
    }

    public List<String> getFullName() {
        return Jsoup.parse(html).select(FULL_NAME_SELECTOR).eachText();
    }

    public List<String> getUrl() {
        return Jsoup.parse(html).select(URL_SELECTOR).eachAttr("href");
    }
}
