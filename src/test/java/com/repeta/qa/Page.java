package com.repeta.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page extends PageObject{

    public Page(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    public boolean containsText(String text){
        return driver.findElement(By.tagName("body")).getText().contains(text);
    }
}
