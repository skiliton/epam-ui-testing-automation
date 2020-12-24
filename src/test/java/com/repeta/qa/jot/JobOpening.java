package com.repeta.qa.jot;

import com.repeta.qa.jobopening.JobOpeningPage;
import com.repeta.qa.PageObject;
import com.repeta.qa.WebDriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobOpening extends PageObject {

    private int index;

    public JobOpening(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    private String previewCardCssSelector(){
        return ".search-result > ul > li:nth-child("+(index+1)+")";
    }
    public JobOpening(WebDriver driver,int timeout, int index){
        super(driver,timeout);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public JobOpeningPage clickTitle(){
        By titleLink = titleLink();
        driver.findElement(titleLink).click();
        return new JobOpeningPage(driver, WebDriverContext.TIMEOUT);
    }

    private By titleLink() {
        return By.cssSelector(previewCardCssSelector()+" .search-result__item-name");
    }

    public JobOpeningPage apply(){
        By applyLink = By.cssSelector(previewCardCssSelector()+" .search-result__item-apply");
        driver.findElement(applyLink).click();
        return new JobOpeningPage(driver,WebDriverContext.TIMEOUT);
    }

    public String getDescription(){
        By description = By.cssSelector(previewCardCssSelector()+" .search-result__item-description");
        return driver.findElement(description).getText();
    }

    public String getTitle(){
        return driver.findElement(titleLink()).getText();
    }

    public String getLocation(){
        By location = By.cssSelector(previewCardCssSelector()+" .search-result__location");
        return driver.findElement(location).getText();
    }

}
