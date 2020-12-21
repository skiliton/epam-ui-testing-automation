package com.repeta.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchBar {

    private WebDriver driver;

    private By skillsDropdown = By.cssSelector(".selected-params");

    private By skills = By.cssSelector(".checkbox-custom-label");

    private By skillTags = By.cssSelector(".filter-tag");

    private By typeTags = By.cssSelector(".recruiting-search__filter-label");

    private By locationSelector = By.cssSelector(".select2-selection");

    private By locationField = By.cssSelector(".select2-search__field");

    private By keywordOrIdField = By.cssSelector(".recruiting-search__input");

    private By submitButton = By.cssSelector(".recruiting-search__submit");

    public SearchBar(WebDriver driver){
        this.driver = driver;
    }

    public void openSkillsDropdownMenu(){
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

    public void submit(){
        driver.findElement(submitButton).click();
    };
}
