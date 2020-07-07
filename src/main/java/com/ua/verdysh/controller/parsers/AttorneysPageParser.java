package com.ua.verdysh.controller.parsers;

import org.jsoup.Jsoup;

import java.util.List;

public class AttorneysPageParser {

    private static final String FULL_NAME_SELECTOR = "h4:nth-child(2)";
    private static final String PHONE_SELECTOR = "p.meta.show-for-large-up:nth-child(2) > a:nth-child(1)";
    private static final String MAIL_SELECTOR = "p.meta.show-for-large-up:nth-child(2) > a:nth-child(3)";
    private static final String URL_SELECTOR = "ul.small-block-grid-2.medium-block-grid-4.large-block-grid-5 div.attorney-card > a";

    private final String html;

    public AttorneysPageParser(String html) {
        this.html = html;
    }

    public List<String> getFullName() {
        return Jsoup.parse(html).select(FULL_NAME_SELECTOR).eachText();
    }

    public List<String> getPhone() {
        return Jsoup.parse(html).select(PHONE_SELECTOR).eachText();
    }

    public List<String> getMail() {
        return Jsoup.parse(html).select(MAIL_SELECTOR).eachText();
    }

    public List<String> getUrl() {
        return Jsoup.parse(html).select(URL_SELECTOR).eachAttr("href");
    }
}
