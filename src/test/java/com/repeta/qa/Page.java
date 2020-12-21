package com.repeta.qa;

import org.openqa.selenium.WebDriver;

public class Page {

    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public boolean containsText(String text){
        return driver.getPageSource().contains(text);
    }
}
