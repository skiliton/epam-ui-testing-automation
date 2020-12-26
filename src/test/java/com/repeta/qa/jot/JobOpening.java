package com.repeta.qa.jot;

import com.repeta.qa.jobopening.JobOpeningPage;
import com.repeta.qa.PageObject;
import com.repeta.qa.WebDriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobOpening extends PageObject {

    private int index;

    private String previewCardCssSelector(){
        return ".search-result > ul > li:nth-child("+(index+1)+")";
    }

    private By titleLink() {
        return By.cssSelector(previewCardCssSelector()+" .search-result__item-name");
    }

    private By applyLink() {
        return By.cssSelector(previewCardCssSelector()+" .search-result__item-apply");
    }

    private By description() {
        return By.cssSelector(previewCardCssSelector()+" .search-result__item-description");
    }

    private By location() {
        return By.cssSelector(previewCardCssSelector()+" .search-result__location");
    }

    public JobOpening(WebDriver driver, int timeout) {
        super(driver, timeout);
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
        driver.findElement(titleLink()).click();
        return new JobOpeningPage(driver, WebDriverContext.TIMEOUT);
    }

    public JobOpeningPage apply(){
        driver.findElement(applyLink()).click();
        return new JobOpeningPage(driver,WebDriverContext.TIMEOUT);
    }

    public String getDescription(){
        return driver.findElement(description()).getText();
    }

    public String getTitle(){
        return driver.findElement(titleLink()).getText();
    }

    public String getLocation(){
        return driver.findElement(location()).getText();
    }



}
