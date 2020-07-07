package com.ua.verdysh.controller.helpers;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ScraperHelper {

    private ScraperHelper() {}

    public static Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getText(Document document, String selector) {
        if (document != null) {
            return document.select(selector).text();
        }
        return StringUtils.EMPTY;
    }
}
