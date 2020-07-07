package com.ua.verdysh.controller;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class VcfBodyParser {

    private static final String ADDRESS_MATCHER = "ADR;";
    private static final String MAIL_MATCHER = "EMAIL;";
    private static final String PHONE_MATCHER = "TEL;";

    private static final String ADDRESS_SEPARATOR = ":;;";
    private static final String MAIL_SEPARATOR = ":";
    private static final String PHONE_SEPARATOR = ":";

    private final String[] vcfBody;

    public VcfBodyParser(String[] vcfBody) {
        this.vcfBody = vcfBody;
    }

    public String getPhone() {
        return cutElement(PHONE_MATCHER, PHONE_SEPARATOR);
    }

    public String getAddress() {
        return cutElement(ADDRESS_MATCHER, ADDRESS_SEPARATOR);
    }

    public String getMail() {
        return cutElement(MAIL_MATCHER, MAIL_SEPARATOR);
    }

    private String cutElement(String matcher, String separator) {
        return Arrays.stream(vcfBody)
                .filter(v -> v.contains(matcher))
                .map(v -> StringUtils.substringAfterLast(v, separator))
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }
}
