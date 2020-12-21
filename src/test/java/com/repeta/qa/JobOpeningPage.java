package com.repeta.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobOpeningPage extends Page {

    public JobOpeningPage(WebDriver driver) {
        super(driver);
    }

    public String getJobTitle(){
        return driver.findElement(By.cssSelector("#main > article > div > header > h1")).getText();
    }
}
