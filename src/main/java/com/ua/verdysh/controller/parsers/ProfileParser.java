package com.ua.verdysh.controller.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.StringJoiner;

public class ProfileParser {

    private static final String PHOTO_SELECTOR = "img.attorney-image.show-for-large-up.wp-post-image";
    private static final String JOB_TITLE_SELECTOR = "h1.entry-title small";
    private static final String DESCRIPTION_SELECTOR = "div.entry-content div:nth-child(1) > p";
    private static final String EDUCATION_SELECTOR = "article.widget:nth-child(3) ul:nth-child(2) > li";
    private static final String PRACTICE_AREAS_SELECTOR = "article.widget:nth-child(1) > ul:nth-child(2) a";
    private static final String VCF_URL_SELECTOR = "p.meta > a:nth-child(2)";

    public String getPhoto(String html) {
        return Jsoup.parse(html).select(PHOTO_SELECTOR).text();
    }

    public String getJobTitle(String html) {
        return Jsoup.parse(html).select(JOB_TITLE_SELECTOR).text();
    }

    public String getVcf(String html) {
        return Jsoup.parse(html).select(VCF_URL_SELECTOR).text();
    }

    public StringJoiner getDescription(String html) {
        return getElementsStringJoiner(html, DESCRIPTION_SELECTOR);
    }

    public StringJoiner getEducation(String html) {
        return getElementsStringJoiner(html, EDUCATION_SELECTOR);
    }

    public StringJoiner getPracticeAreas(String html) {
        return getElementsStringJoiner(html, PRACTICE_AREAS_SELECTOR);
    }

    private StringJoiner getElementsStringJoiner(String html, String selector) {
        StringJoiner result = new StringJoiner(" : ");
        List<String> desc = Jsoup.parse(html).select(selector).eachText();
        desc.forEach(result::add);
        return result;
    }
}
