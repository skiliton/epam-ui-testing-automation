package com.repeta.qa.stepdefs;

import com.repeta.qa.jot.JobSearchBar;
import com.repeta.qa.WebDriverContext;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JobSearchStepDefinitions {

    private JobSearchBar searchBar;

    @Before
    public void setUp(){
        searchBar = new JobSearchBar(WebDriverContext.getDriver(),WebDriverContext.TIMEOUT);
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

}
