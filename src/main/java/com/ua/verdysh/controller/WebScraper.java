package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ScraperHelper;
import com.ua.verdysh.controller.parsers.AttorneysPageParser;
import com.ua.verdysh.controller.parsers.EmeritusPageParser;
import com.ua.verdysh.controller.parsers.ProfileParser;
import com.ua.verdysh.controller.parsers.SupportPageParser;
import com.ua.verdysh.model.PartnerProfile;
import com.ua.verdysh.model.Profile;
import com.ua.verdysh.model.ProfilesGroup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    private static final String ATTORNEYS_PAGE = "https://www.helsell.com/our-team/";
    private static final String EMERITUS_PAGE = "https://www.helsell.com/our-team/emeritus/";
    private static final String SUPPORT_PAGE = "https://www.helsell.com/our-team/legal-support/";
    private static final String ADMINISTRATION_PAGE = "https://www.helsell.com/our-team/administration/";
    private static final String GROUP_NAME_SELECTOR = "h1.entry-title";
    private static final String VCF_LINE_DELIMITER = "\n";

    public List<ProfilesGroup> scrapeProfiles() {
        List<ProfilesGroup> result = new ArrayList<>();

        ProfilesGroup attorneys = scrapeAttorneys();
        result.add(attorneys);

        ProfilesGroup emeritus = scrapeEmeritus();
        result.add(emeritus);

        ProfilesGroup support = scrapeSupport();
        result.add(support);
        return result;
    }

    private ProfilesGroup scrapeSupport() {

        Document document = ScraperHelper.getDocument(SUPPORT_PAGE);
        String groupName = getGroupName(document);
        SupportPageParser parser = new SupportPageParser(document.html());
        List<PartnerProfile> profiles = getSupportProfiles(parser);
        ProfilesGroup group = new ProfilesGroup(groupName);
        group.setProfiles(profiles);
        return group;
    }

    private ProfilesGroup scrapeEmeritus() {

        Document document = ScraperHelper.getDocument(EMERITUS_PAGE);
        String groupName = getGroupName(document);
        EmeritusPageParser parser = new EmeritusPageParser(document.html());
        List<PartnerProfile> profiles = getEmeritusProfiles(parser);
        ProfilesGroup group = new ProfilesGroup(groupName);
        group.setProfiles(profiles);
        return group;
    }

    private ProfilesGroup scrapeAttorneys() {

        Document document = ScraperHelper.getDocument(ATTORNEYS_PAGE);
        String groupName = getGroupName(document);
        AttorneysPageParser parser = new AttorneysPageParser(document.html());
        List<PartnerProfile> profiles = getAttorneysProfiles(parser);
        ProfilesGroup group = new ProfilesGroup(groupName);
        group.setProfiles(profiles);
        return group;
    }

    private List<PartnerProfile> getSupportProfiles(SupportPageParser parser) {

        List<PartnerProfile> result = new ArrayList<>();

        List<String> names = parser.getFullName();
        List<String> phones = parser.getPhone();
        List<String> mails = parser.getMail();

        for (int count = 0; count < names.size(); count++) {
            PartnerProfile profile = new PartnerProfile();

            profile.setFullName(names.get(count));
            profile.setPhone(phones.get(count));
            profile.setMail(mails.get(count));

            result.add(profile);
        }

        return result;
    }

    private List<PartnerProfile> getEmeritusProfiles(EmeritusPageParser parser) {

        List<PartnerProfile> result = new ArrayList<>();

        List<String> names = parser.getFullName();
        List<String> urls = parser.getUrl();

        for (int count = 0; count < names.size(); count++) {
            PartnerProfile profile = new PartnerProfile();

            profile.setFullName(names.get(count));
            profile.setUrl(urls.get(count));

            result.add(profile);
        }

        setEmeritusProfileFields(result);
        return result;
    }

    private List<PartnerProfile> getAttorneysProfiles(AttorneysPageParser parser) {

        List<PartnerProfile> result = new ArrayList<>();

        List<String> names = parser.getFullName();
        List<String> phones = parser.getPhone();
        List<String> mails = parser.getMail();
        List<String> urls = parser.getUrl();

        for (int count = 0; count < names.size(); count++) {
            PartnerProfile profile = new PartnerProfile();

            profile.setFullName(names.get(count));
            profile.setUrl(urls.get(count));
            profile.setPhone(phones.get(count));
            profile.setMail(mails.get(count));


            result.add(profile);
        }

        setAtterneysProfilesFields(result);
        return result;
    }

    private void setEmeritusProfileFields(List<PartnerProfile> profiles) {

        ProfileParser parser = new ProfileParser();
        for (PartnerProfile profile : profiles) {
            Document document = ScraperHelper.getDocument(profile.getUrl());
            String html = document != null ? document.html() : "";

            profile.setDescription(parser.getDescription(html));
            profile.setEducation(parser.getEducation(html));
            profile.setJobTitle(parser.getJobTitle(html));
            profile.setPhoto(parser.getPhoto(html));
            profile.setPracticeAreas(parser.getPracticeAreas(html));

            String vcfBody = VcfReader.readFromUrl(parser.getVcf(html));
            VcfBodyParser vcfParser = new VcfBodyParser(vcfBody.split(VCF_LINE_DELIMITER));

            profile.setPhone(vcfParser.getPhone());
            profile.setMail(vcfParser.getMail());
        }
    }

    private void setAtterneysProfilesFields(List<PartnerProfile> profiles) {

        ProfileParser parser = new ProfileParser();
        for (PartnerProfile profile : profiles) {
            Document document = ScraperHelper.getDocument(profile.getUrl());
            String html = document != null ? document.html() : "";

            profile.setDescription(parser.getDescription(html));
            profile.setEducation(parser.getEducation(html));
            profile.setJobTitle(parser.getJobTitle(html));
            profile.setPhoto(parser.getPhoto(html));
            profile.setPracticeAreas(parser.getPracticeAreas(html));

            String vcfBody = VcfReader.readFromUrl(parser.getVcf(html));
            VcfBodyParser vcfParser = new VcfBodyParser(vcfBody.split(VCF_LINE_DELIMITER));

            profile.setAddress(vcfParser.getAddress());
        }
    }

    private String getGroupName(Document document) {
        return ScraperHelper.getText(document, GROUP_NAME_SELECTOR);
    }
}
