package com.repeta.qa;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;
import java.util.List;

public class WebDriverContext {

    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";
    public static final String SAFARI = "safari";
    public static final String EDGE = "edge";
    public static final String OPERA = "opera";
    public static final String IE = "ie";
    public static final int    TIMEOUT = new Integer(System.getProperty("ui-test.timeout","10"));

    private static WebDriver driver = null;

    private WebDriverContext(){}

    public static WebDriver getDriver(){
        if(driver==null){
            String browser = System.getProperty("ui-test.browser",FIREFOX);
            switch (browser){
                case CHROME:
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                case SAFARI:
                    driver = new SafariDriver();
                    break;
                case EDGE:
                    driver = new EdgeDriver();
                    break;
                case OPERA:
                    driver = new SafariDriver();
                    break;
                case IE:
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Cannot find browser with name: "+browser);
            }
        }
        return driver;
    }

    @After
    public static void tearDown(){
        driver.quit();
        driver=null;
    }

}
