package com.repeta.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.regex.Pattern;

public class JobOpening {

    private static final String TAG_RELOCATION = "Relocation";
    private static final String TAG_OFFICE = "Office";
    private static final String TAG_REMOTE = "Remote";

    private WebDriver driver;

    private By applyLink;

    private By titleLink;

    private By description;

    private By location;

    private By icons;

    public JobOpening(WebDriver driver, int index){
        this.driver = driver;
        String jobOpening = ".search-result > ul > li:nth-child("+index+")";
        applyLink = By.cssSelector(jobOpening+" .search-result__item-apply");
        titleLink = By.cssSelector(jobOpening+" .search-result__item-name");
        description = By.cssSelector(jobOpening+" .search-result__item-description");
        location = By.cssSelector(jobOpening+" .search-result__location");
        icons = By.cssSelector(jobOpening+" .search-result__item-icon");
    }

    public JobOpeningPage clickTitle(){
        driver.findElement(titleLink).click();
        return new JobOpeningPage(driver);
    }

    public JobOpeningPage apply(){
        driver.findElement(applyLink).click();
        return new JobOpeningPage(driver);
    }

    public String getDescription(){return driver.findElement(description).getText();}

    public String getTitle(){return driver.findElement(titleLink).getText();}

    public String getLocation(){return driver.findElement(location).getText();}

    public void hoverOverTagIcon(String tag){
        List<WebElement> iconList = driver.findElements(icons);
        for (WebElement icon: iconList) {
            boolean containsTagText = Pattern.compile(Pattern.quote(tag), Pattern.CASE_INSENSITIVE).matcher(icon.getAttribute("data-title")).find();
            if(containsTagText){
                Actions actions = new Actions(driver);
                actions.moveToElement(icon).perform();
                return;
            }
        }
        throw new IllegalArgumentException("Cannot find tag to hover over");
    }

    public String getActiveTagHint(){
        List<WebElement> iconList = driver.findElements(icons);
        for (WebElement icon: iconList) {
            if(icon.getAttribute("innerHTML").contains("::after")){
                return icon.getAttribute("data-title");
            }
        }
        throw new IllegalArgumentException("Cannot find active hint");}

}
