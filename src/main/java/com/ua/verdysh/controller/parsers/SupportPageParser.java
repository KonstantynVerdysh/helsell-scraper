package com.ua.verdysh.controller.parsers;

import org.jsoup.Jsoup;

import java.util.List;

public class SupportPageParser {

    private static final String FULL_NAME_SELECTOR = "tbody > tr td:nth-child(1)";
    private static final String PHONE_SELECTOR = "tbody > tr td:nth-child(2)";
    private static final String MAIL_SELECTOR = "tbody > tr td:nth-child(3)";

    private final String html;

    public SupportPageParser(String html) {
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
}
