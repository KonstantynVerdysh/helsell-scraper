package com.ua.verdysh.controller.parsers;

import org.jsoup.Jsoup;

import java.util.List;

public class AdministrationPageParser{

    private static final String FULL_NAME_SELECTOR = "tbody > tr td:nth-child(1)";
    private static final String PHONE_SELECTOR = "tbody > tr td:nth-child(3)";
    private static final String MAIL_SELECTOR = "tbody > tr td:nth-child(4)";
    private static final String JOB_TITLE_SELECTOR = "tbody > tr td:nth-child(2)";

    public List<String> getFullName(String html) {
        return Jsoup.parse(html).select(FULL_NAME_SELECTOR).eachText();
    }

    public List<String> getPhone(String html) {
        return Jsoup.parse(html).select(PHONE_SELECTOR).eachText();
    }

    public List<String> getMail(String html) {
        return Jsoup.parse(html).select(MAIL_SELECTOR).eachText();
    }

    public List<String> getJobTitle(String html) {
        return Jsoup.parse(html).select(JOB_TITLE_SELECTOR).eachText();
    }
}
