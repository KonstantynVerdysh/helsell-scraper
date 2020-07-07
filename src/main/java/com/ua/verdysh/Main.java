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



// basic profiles
// tbody > tr td:nth-child(1) name
// tbody > tr td:nth-child(2) phone
// tbody > tr td:nth-child(3) mail

// administration
// tbody > tr td:nth-child(1) name
// tbody > tr td:nth-child(2) job title
// tbody > tr td:nth-child(3) phone
// tbody > tr td:nth-child(4) mail

// fullprofile
// ul.small-block-grid-2.medium-block-grid-4.large-block-grid-5 div.attorney-card > a   attorneys url

// ul.small-block-grid-2.medium-block-grid-4.large-block-grid-5 a   emeritus url