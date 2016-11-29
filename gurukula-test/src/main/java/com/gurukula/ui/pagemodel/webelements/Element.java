package com.gurukula.ui.pagemodel.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {

    protected WebDriver driver;
    protected By by;
    protected int waitTime = 10;

    public Element(WebDriver driver, By by) {
        this.driver = driver;
        this.by = by;
    }

    public By getBy() {
        return by;
    }

    public boolean isVisible() {
        return isDisplayed();
    }

    public boolean isDisplayed() {
        return this.driver.findElement(by).isDisplayed();
    }

    public boolean isEnabled() {
        return this.driver.findElement(by).isEnabled();
    }

    public boolean isSelected() {
        return this.driver.findElement(by).isSelected();
    }

    public Element waitForElementPresent(int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return this;
    }

    public Element waitForElementVisible(int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return this;
    }

    public Element waitForElementPresent() {
        return waitForElementPresent(waitTime);
    }

    public Element waitForElementVisible() {
        return waitForElementVisible(waitTime);
    }

    public Element waitForElementPresentAndVisible(int waitTime) {
        return waitForElementPresent(waitTime).waitForElementVisible(waitTime);
    }

    public Element waitForElementPresentAndVisible() {
        return waitForElementPresentAndVisible(waitTime);
    }
}
