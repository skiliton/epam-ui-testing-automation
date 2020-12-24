package com.repeta.qa.stepdefs;

import com.repeta.qa.JoinOurTeamPage;
import com.repeta.qa.Loadable;
import com.repeta.qa.Page;
import com.repeta.qa.WebDriverContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class PageStepDefinitions {

    private WebDriver driver;

    @io.cucumber.java.Before
    public void setUp(){
         driver = WebDriverContext.getDriver();
    }

    @Given("I am on {string} page")
    public void i_am_on_page(String pageTitle) {
        if(pageTitle.equals("Join our Team")){

            Loadable page = new JoinOurTeamPage(driver);
            page.loadPage();
        }else {
            throw new IllegalArgumentException("Page "+pageTitle+" cannot be loaded");
        }
    }

    @Then("I should see message {string}")
    public void i_should_see_message(String message) {
        Page page = new Page(driver);
        assertTrue(page.containsText(message));
    }

}
