package com.ua.verdysh;

import com.ua.verdysh.controller.WebScraper;
import com.ua.verdysh.model.PartnerProfile;
import com.ua.verdysh.model.ProfilesGroup;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebScraper scraper = new WebScraper();
        List<ProfilesGroup> groups = scraper.scrapeProfiles();
        for (ProfilesGroup group : groups) {
            System.out.println(group.getGroupName());
            for (PartnerProfile profile : group.getProfiles()) {
                System.out.println(profile.getUrl());
                System.out.println(profile.getFullName());
                System.out.println(profile.getJobTitle());
                System.out.println(profile.getDescription());
                System.out.println(profile.getPhoto());
                System.out.println(profile.getAddress());
                System.out.println(profile.getEducation());
                System.out.println(profile.getPracticeAreas());
                System.out.println(profile.getMail());
                System.out.println(profile.getPhone());
                System.out.println();
            }
        }
    }
}
