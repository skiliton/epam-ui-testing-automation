package com.repeta.qa.jobopening;

import com.repeta.qa.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class JobOpeningPage extends Page {

    @FindBy(css="#main > article > div > header > h1")
    private By jobPosition;

    public JobOpeningPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    public String getJobTitle(){
        return driver.findElement(jobPosition).getText();
    }
}
