package com.repeta.qa.jot;

import com.repeta.qa.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;

public class JobSearchBar extends PageObject {


    private By skillsDropdown = By.cssSelector(".selected-params");

    private By skills = By.cssSelector(".checkbox-custom-label");

    private By skillTags = By.cssSelector(".filter-tag");

    private By typeTags = By.cssSelector(".recruiting-search__filter-label");

    private By locationSelector = By.cssSelector(".select2-selection");

    private By locationField = By.cssSelector(".select2-search__field");

    private By keywordOrIdField = By.cssSelector(".recruiting-search__input");

    private By submitButton = By.cssSelector(".recruiting-search__submit");

    private By highlightedLocation =  By.cssSelector(".select2-results__option--highlighted");

    public JobSearchBar(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    public void openCloseSkillsDropdownMenu(){
        driver.findElement(skillsDropdown).click();
    }

    public void selectTag(String tag){
        for (WebElement typeTagWE: driver.findElements(typeTags)){
            if(typeTagWE.getText().contains(tag)){
                typeTagWE.click();
                return;
            }
        }
    }

    public void selectSkill(String skill){
        for (WebElement skillWE : driver.findElements(skills)) {
            if(skillWE.getText().contains(skill)){
                skillWE.click();
                return;
            }
        }
    }

    public void enterLocation(String location){
        driver.findElement(locationSelector).click();
        driver.findElement(locationField).sendKeys(location);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(highlightedLocation));
        driver.findElement(highlightedLocation).click();
    }

    public void enterKeywordOrID(String text){
        driver.findElement(keywordOrIdField).sendKeys(text);
    }

    public List<String> getSkillTags(){
        return driver
            .findElements(skillTags)
            .stream()
            .map(we->we.getAttribute("data-value"))
            .collect(Collectors.toList());
    }

    public JoinOurTeamPage submit()
    {
        driver.findElement(submitButton).click();
        return new JoinOurTeamPage(driver,timeout);
    };

    public void hoverOverTagIcon(String tag){
        List<WebElement> iconList = driver.findElements(typeTags);
        for (WebElement icon: iconList) {
            String text = icon.getText();
            if(text.contains(tag)){
                Actions actions = new Actions(driver);
                actions.moveToElement(icon).perform();
                return;
            }
        }
        throw new IllegalArgumentException("Cannot find tag to hover over");
    }



    public String getActiveTagHint(){
        List<WebElement> iconList = driver.findElements(typeTags);
        for (WebElement icon: iconList) {
            JavascriptExecutor js = (JavascriptExecutor)driver;
            String innerHTML = (String) js.executeScript("return arguments[0].innerHTML;",icon);
            if(innerHTML.contains("::after")){
                return icon.getAttribute("data-title");
            }
        }
        throw new IllegalArgumentException("Cannot find active hint");
    }
}
