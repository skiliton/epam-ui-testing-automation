package com.repeta.qa;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    private WebDriver driver;

    private JoinOurTeamPage page;

    private String correspondingJobOpeningTitle;

    private SearchBar searchBar;


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        driver = new FirefoxDriver();
        page = new JoinOurTeamPage(driver);

        searchBar = new SearchBar(driver);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Then("I should see job openings")
    public void i_should_see_job_openings() {
        assertTrue(page.getJobOpenings().size()>0);
    }

    @Then("I should see {int} job opening(s)")
    public void i_should_see_job_openings(int joAmount) {
        assertEquals(joAmount,page.getJobOpenings().size());
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
        JobOpeningPage page = new JobOpeningPage(driver);
        assertTrue(page.getJobTitle().contains(correspondingJobOpeningTitle));
    }

    @When("I hover over the {string} tag icon on the search bar")
    public void i_hover_over_the_tag_icon(String tag){
        searchBar.hoverOverTagIcon(tag);
    }

    @Then("I should see {string} hint underneath the tag icon on the search bar")
    public void i_should_see_hint_underneath_the_tag_icon_of_the_job_opening(String text){
        assertEquals(text,searchBar.getActiveTagHint());
    }


    private List<String> selectRandomList(String[] arr){
        int l = ThreadLocalRandom.current().nextInt(0, arr.length+1);
        List<String> arrList = new LinkedList<>();
        for (int i=0; i<l;i++){
            int index = ThreadLocalRandom.current().nextInt(0, arr.length);
            if(arr[index]!=null){
                arrList.add(arr[index]);
            }
        }
        return arrList;
    }

    @Given("I entered {string} keyword")
    public void i_entered_keyword(String keyword) {
        searchBar.enterKeywordOrID(keyword);
    }

    @Given("I entered {int} job ID")
    public void i_entered_job_id(Integer id) {
        i_entered_keyword(String.valueOf(id));
    }

    @Given("I entered {string} location")
    public void i_entered_location(String location) {
        searchBar.enterLocation(location);
    }

    @Given("I selected skills:")
    public void i_selected_skills(List<String> skills) {
        skills.forEach(searchBar::selectSkill);
    }

    @Given("I selected tags:")
    public void i_selected_tags(List<String> tags) {
        tags.forEach(searchBar::selectTag);
    }

    @When("I submit search request")
    public void i_submit_search_request() {
        searchBar.submit();
    }

    @Given("I entered any location")
    public void i_entered_any_location() {
        String[] locations = {"Kyiv", "Toronto", "New York", "Vienna", "Los Angeles"};
        int i = ThreadLocalRandom.current().nextInt(0, locations.length);
        i_entered_location(locations[i]);
    }

    @Given("I selected any skills")
    public void i_selected_any_skills() {
        String[] skills = {
                "Software Engineering/Technology",
                "Young Specialist",
                "Management",
                "Training & Coaching",
                "User Experience & Design"
        };
        searchBar.openCloseSkillsDropdownMenu();
        i_selected_skills(selectRandomList(skills));
        searchBar.openCloseSkillsDropdownMenu();
    }

    @Given("I selected any tags")
    public void i_selected_any_tags() {
        String[] tags = {
                "Open to Relocation",
                "Office",
                "Remote",
        };
        i_selected_tags(selectRandomList(tags));
    }

    @Given("I opened skills dropdown menu")
    public void i_opened_skills_dropdown_menu(){
        searchBar.openCloseSkillsDropdownMenu();
    }

    @When("I select {string} skill")
    public void i_select_skill(String skill){
        searchBar.selectSkill(skill);
    }

    @Then("I should see {string} skill tag under search bar")
    public void i_should_see_skill_tag_under_search_bar(String skill){
        List<String> skillTags = searchBar.getSkillTags();
        assertTrue(skillTags.contains(skill));
    }

    @Given("I found any job openings")
    public void i_found_any_job_openings(){
        JoinOurTeamPage page = new JoinOurTeamPage(driver);
        page.loadPage();
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
