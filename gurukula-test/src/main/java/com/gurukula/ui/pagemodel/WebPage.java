package com.gurukula.ui.pagemodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebPage {

    protected WebDriver driver;
    protected String url = null;
    protected int waitTime = 10;

    public WebPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void open() {
        if (url != null)
            open(url);
        else
            throw new NullPointerException("The url for this page was not specified!");

    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public void open(String url) {
        driver.navigate().to(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public WebPage waitForTitle(String title) {
        final String ftitle = title.toLowerCase();
        (new WebDriverWait(driver, waitTime)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(ftitle);
            }
        });
        return this;
    }

    public abstract void waitForPageLoad();

    public abstract void validate();
}
