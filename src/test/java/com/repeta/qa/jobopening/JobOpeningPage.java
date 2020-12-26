package com.repeta.qa.jobopening;

import com.repeta.qa.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobOpeningPage extends Page {


    private final By jobPosition = By.cssSelector("#main > article > div > header > h1");

    public JobOpeningPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    public String getJobTitle(){
        return driver.findElement(jobPosition).getText();
    }
}
