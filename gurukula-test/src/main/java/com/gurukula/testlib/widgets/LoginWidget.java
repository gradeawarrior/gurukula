package com.gurukula.testlib.widgets;

import com.github.seleniumpm.webelements.Link;
import com.github.seleniumpm.webelements.Widget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginWidget extends Widget {
    public Link loginLink;
    public Link registerAccountLink;

    public LoginWidget(WebDriver driver, By locator) {
        super(driver, locator);
        loginLink = new Link(driver, By.xpath("//div/a[@href='#/login']"));
        registerAccountLink = new Link(driver, By.xpath("//div/a[@href='#/register']"));
    }

    public LoginWidget waitForPresentAndVisible() {
        super.waitForPresentAndVisible();
        loginLink.waitForPresentAndVisible();
        registerAccountLink.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        super.presentAndVisible();
        loginLink.presentAndVisible();
        registerAccountLink.presentAndVisible();
    }
}
