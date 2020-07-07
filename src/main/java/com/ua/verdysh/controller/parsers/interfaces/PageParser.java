package com.ua.verdysh.controller.parsers.interfaces;

import java.util.List;

public interface PageParser {
    List<String> getFullName(String html);
    List<String> getPhone(String html);
    List<String> getMail(String html);
}
