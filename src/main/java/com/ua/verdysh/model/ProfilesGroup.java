package com.ua.verdysh.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfilesGroup {
    private String url;
    private String groupName;
    private List<Profile> profiles;
}
