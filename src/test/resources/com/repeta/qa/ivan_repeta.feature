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
    When I submit search request
    Then I should see job openings
    And each job opening should contain 'Senior' keyword in the job title or job description
    And each job opening should contain 'Toronto' location

  @test-this
  Scenario: User tries to find  a job opening(s) with non existent combination and fails
    Given
    * I selected tags:
      |Office|
    * I entered 'GSDgdfh' keyword
    * I entered 'Kyiv' location
    * I selected skills:
        |Software Engineering/Technology|
        |Young Specialist               |

    When I submit search request
    Then I should see 0 job openings
    And I should see message 'Sorry, your search returned no results. Please try another combination.'

  Scenario: User applies in the job opening preview
    Given I found any job openings
    When I apply for the 1 job opening
    Then I should see corresponding job opening page


  Scenario: User clicks on the job title in the job opening preview
    Given I found any job openings
    When I click on the title of the 1 job opening
    Then I should see corresponding job opening page


#  Scenario Outline: User hovers over the tag icon on the search bar
#    When I hover over the '<tag>' tag icon on the search bar
#    Then I should see '<text>' hint underneath the tag icon on the search bar
#
#    Examples:
#    |tag       |text|
#    |Office    |Work from office  |
#    |Remote    |You can work from home or anywere in the world if you apply for remote position|

  Scenario: User tries to find a job opening using valid Job ID and succeeds
    Given
    * I entered 57246 job ID
    * I entered any location
    * I selected any skills
    * I selected any tags
    When I submit search request
    Then I should see 1 job openings


  Scenario: User tries to find a job opening by nonexistent Job ID and fails
    Given
    * I selected any tags
    * I entered 65271 job ID
    * I entered any location
    * I selected any skills
    When I submit search request
    Then I should see 0 job openings
    And I should see message 'Sorry, your search returned no results. Please try another combination.'


  Scenario Outline: User selects skills in the search bar
    Given I opened skills dropdown menu
    When I select '<skill>' skill
    Then I should see '<skill>' skill tag under search bar

    Examples:
    |skill                           |
    |Administrative & Finance        |
    |Cloud & DevOps                  |
    |Consulting & Business Analysis  |
    |Data Analysis & Digital Strategy|