package com.repeta.qa.stepdefs;

import com.repeta.qa.jot.JobOpening;
import com.repeta.qa.jobopening.JobOpeningPage;
import com.repeta.qa.jot.JoinOurTeamPage;
import com.repeta.qa.WebDriverContext;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JobOpeningStepDefinitions {

    private WebDriver driver;
    private String correspondingJobOpeningTitle;

    private JoinOurTeamPage page;

    @Before
    public void setUp(){
        driver  = WebDriverContext.getDriver();
        page = new JoinOurTeamPage(driver, WebDriverContext.TIMEOUT);
    }

    @Given("I found any job openings")
    public void i_found_any_job_openings(){
        page.loadPage();
    }

    @Then("I should see job openings")
    public void i_should_see_job_openings() {
        int actualJoAmount = page.getJobOpenings().size();
        assertTrue(actualJoAmount >0);
    }

    @Then("I should see {int} job opening(s)")
    public void i_should_see_job_openings(int joAmount) {
        int actualJoAmount = page.getJobOpenings().size();
        assertEquals(joAmount, actualJoAmount);
    }


    @Then("each job opening should contain {string} keyword in the job title or job description")
    public void each_job_opening_should_contain_keyword_in_the_job_title_or_job_description(String keyword) {
        List<JobOpening> jobOpenings = page.getJobOpenings();
        jobOpenings.forEach(jo->assertTrue(jo.getDescription().contains(keyword)||jo.getTitle().contains(keyword)));
    }

    @Then("each job opening should contain {string} location")
    public void each_job_opening_should_contain_location(String location) {
        List<JobOpening> jobOpenings = page.getJobOpenings();
        jobOpenings.forEach(jo->assertTrue(jo.getLocation().contains(location)||jo.getTitle().contains(location)));
    }

    @When("I click on the title of the {int} job opening")
    public void i_apply_for_a_job(int joIndex) {
        JobOpening jo = page.getJobOpenings().get(joIndex-1);
        correspondingJobOpeningTitle = jo.getTitle();
        jo.clickTitle();
    }

    @When("I apply for the {int} job opening")
    public void i_click_on_the_job_title(int joIndex) {
        JobOpening jo = page.getJobOpenings().get(joIndex-1);
        correspondingJobOpeningTitle = jo.getTitle();
        jo.apply();
    }

    @Then("I should see corresponding job opening page")
    public void i_should_see_corresponding_job_opening_page() {
        if(correspondingJobOpeningTitle==null){
            throw new IllegalStateException("Corresponding job opening title is not set. You should first open job opening page through job opening preview");
        }
        JobOpeningPage page = new JobOpeningPage(driver,WebDriverContext.TIMEOUT);
        assertTrue(page.getJobTitle().contains(correspondingJobOpeningTitle));
    }

}
