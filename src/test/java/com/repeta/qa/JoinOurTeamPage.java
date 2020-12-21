package com.repeta.qa;

import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;

public class JoinOurTeamPage extends Page implements Loadable{

    private static final String URL = "https://www.epam.com/careers/job-listings";

    public JoinOurTeamPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean loadPage() {
        String currentUrl = driver.getCurrentUrl();
        driver.get(URL);
        return !currentUrl.equals(URL);
    }

    public List<JobOpening> getJobOpenings(){
        return new LinkedList<>();
    }

    public SearchBar getSearchBar(){
        return new SearchBar(driver);
    }
}
