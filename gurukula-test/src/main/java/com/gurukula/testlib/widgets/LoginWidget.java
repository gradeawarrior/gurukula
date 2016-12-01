package com.gurukula.testlib.widgets;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.Link;
import com.gurukula.ui.pagemodel.webelements.Widget;
import org.openqa.selenium.By;

public class LoginWidget extends Widget {
    public Link loginLink;
    public Link registerAccountLink;

    public LoginWidget(Selenium sel, Object locator) {
        super(sel, locator);
        loginLink = new Link(sel, By.xpath("//div/a[@href='#/login']"));
        registerAccountLink = new Link(sel, By.xpath("//div/a[@href='#/register']"));
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