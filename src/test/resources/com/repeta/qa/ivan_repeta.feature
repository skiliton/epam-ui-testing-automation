Feature: Job search
  In order to find a job opening
  As a User
  I want the system to filter job openings according to my criteria


  Background:
    Given I am on 'Join our Team' page


  Scenario: User tries to find job opening(s) with valid combination and succeeds
    Given
    * I entered 'Senior' keyword
    * I entered 'Toronto' location
    * I selected skills:
        |Management|
    * I selected tags:
        |Open to Relocation|
    When I submit search request
    Then I should see job openings
    And each job opening should contain 'Senior' keyword in the job title or job description
    And each job opening should contain 'Toronto' location


  Scenario: User tries to find  a job opening(s) with non existent combination and fails
    Given
    * I entered 'Junior' keyword
    * I entered 'Kiev' location
    * I selected skills:
        |Software Engineering/Technology|
        |Young Specialist               |
    * I selected tags:
        |Office|
    When I submit search request
    Then I should see message Sorry, your search returned no results. Please try another combination.


  Scenario: User applies in the job opening preview
    Given I found any job openings
    When I apply for the 1 job opening
    Then I should see corresponding job opening page


  Scenario: User clicks on the job title in the job opening preview
    Given I found any job openings
    When I click on the title of the 1 job opening
    Then I should see corresponding job opening page


  Scenario Outline: User hovers over the tag icon in the job opening preview
    Given I found any job openings
    When I hover over the '<tag>' tag icon of the 1 job opening
    Then I should see '<text>' hint underneath the tag icon of the 1 job opening

    Examples:
    |tag       |text|
    |Relocation|Open to relocation|
    |Office    |Work from office  |
    |Remote    |You can work from home or anywere in the world if you apply for remote position|

  Scenario: User tries to find a job opening using valid Job ID and succeeds
    Given
    * I entered 43627 job ID
    * I entered any location
    * I selected any skills
    * I selected any tags
    When I submit search request
    Then I should see only one job opening


  Scenario: User tries to find a job opening by nonexistent Job ID and fails
    Given
    * I entered 65271 job ID
    * I entered any location
    * I selected any skills
    * I selected any tags
    When I submit search request
    Then I should see message 'Sorry, your search returned no results. Please try another combination.'


  Scenario Outline: User selects skills in the search bar
    Given I opened skills dropdown menu
    When I select '<skill>' skill
    Then I should see '<skill>' skill tag under search bar

    Examples:
    |skill                           |
    |Administrative & Finance        |
    |Cloud & DevOps                  |
    |Consulting & Business analysis  |
    |Data Analysis & Digital Strategy|