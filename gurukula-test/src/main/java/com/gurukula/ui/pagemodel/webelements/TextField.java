package com.gurukula.ui.pagemodel.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextField extends Element {

    public TextField(WebDriver driver, By by) {
        super(driver, by);
    }

    public void type(String txt) {
        sendKeys(txt);
    }

    public void sendKeys(String txt) {
        waitForElementPresentAndVisible();
        driver.findElement(by).sendKeys(txt);
    }

    public void submit() {
        waitForElementPresentAndVisible();
        driver.findElement(by).submit();
    }
}
