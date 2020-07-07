package com.ua.verdysh.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfilesGroup {
    private String groupName;
    private List<PartnerProfile> profiles;

    public ProfilesGroup(String groupName) {
        this.groupName = groupName;
    }
}
