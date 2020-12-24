package com.repeta.qa;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {

    protected WebDriver driver;

    protected int timeout;

    public PageObject(WebDriver driver, int timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public int getTimeout() {
        return timeout;
    }
}
