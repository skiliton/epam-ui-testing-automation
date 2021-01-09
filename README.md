# UI Testing
Ui testing automation for the EPAM site using Selenium WebDriver and Cucumber

## Technologies
Project is created with:
* Java SE 8
* Selenium 3

Project is tested with:
* JUnit 5
* Cucumber LATEST

## Requirements
To run this project, you need to install the folowing packages on your machine:
* Apache Maven version 3.3.9 or higher
* Java SE 8

## Setup & Running
```
$ cd ../epam-ui-testing-automation
$ mvn test -Dui-test.browser=[browserName] -Dui-test.timeout=[timeout_in_sec] -Dwebdriver.[driver_name].driver=/path/to/driver
```
Possible values for ui-test.browser system property: 
* firefox
* chrome
* safari
* opera
* edge
* ie


## Invocation Example
```
$ cd ../epam-ui-testing-automation
$ mvn test -Dui-test.browser=firefox -Dui-test.timeout=7 -Dwebdriver.gecko.driver=/usr/bin/geckodriver
```
