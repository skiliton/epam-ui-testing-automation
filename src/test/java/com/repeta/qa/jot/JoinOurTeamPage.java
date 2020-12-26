package com.repeta.qa.jot;

import com.repeta.qa.Loadable;
import com.repeta.qa.Page;
import com.repeta.qa.jot.JobOpening;
import com.repeta.qa.jot.JobSearchBar;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class JoinOurTeamPage extends Page implements Loadable {

    private static final String URL = "https://www.epam.com/careers/job-listings";

    private By joSearchResults = By.cssSelector(".search-result__item");

    public JoinOurTeamPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    @Override
    public boolean loadPage() {
        String currentUrl = driver.getCurrentUrl();
        driver.get(URL);
        return !currentUrl.equals(URL);
    }

    public List<JobOpening> getJobOpenings(){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
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

    public JobSearchBar getSearchBar(){
        return new JobSearchBar(driver,timeout);
    }

}
