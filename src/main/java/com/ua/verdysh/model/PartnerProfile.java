package com.ua.verdysh.model;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
public class PartnerProfile extends Profile {
    private String url;
    private String jobTitle;
    private String photo;
    private String address;
    private StringJoiner description;
    private StringJoiner education;
    private StringJoiner practiceAreas;
}
