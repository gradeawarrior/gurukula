package com.gurukula.ui.pagemodel.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button extends Element {

    public Button(WebDriver driver, By by) {
        super(driver, by);
    }

    public void click() {
        waitForElementPresentAndVisible();
        driver.findElement(by).click();
    }
}
