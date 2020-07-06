package com.ua.verdysh.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FullProfile extends Profile {
    private String url;
    private String jobTitle;
    private String description;
    private String photo;
    private String address;
    private List<String> education;
    private List<String> practiceAreas;
}
