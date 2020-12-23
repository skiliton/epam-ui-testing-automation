package com.repeta.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class JoinOurTeamPage extends Page implements Loadable{

    private static final String URL = "https://www.epam.com/careers/job-listings";
    private By joSearchResults = By.cssSelector(".search-result__item");

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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(joSearchResults));
        }catch (TimeoutException e){
            return new ArrayList<>();
        }
        List<WebElement> jobOpeningWEList = driver.findElements(joSearchResults);
        ArrayList<JobOpening> jobOpeningList = new ArrayList<>();
        for(int i = 0; i<jobOpeningWEList.size();i++){
            jobOpeningList.add(new JobOpening(driver,i));
        }
        return jobOpeningList;
    }

    public SearchBar getSearchBar(){
        return new SearchBar(driver);
    }
}
