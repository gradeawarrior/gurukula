package com.gurukula.ui.pagemodel.webelements;

import com.gurukula.ui.Selenium;
import org.openqa.selenium.By;

public class TextField extends Element {

    public TextField(Selenium sel, Object locator) {
        super(sel, locator);
    }

    public void type(String txt) {
        sendKeys(txt);
    }

    public void sendKeys(String txt) {
        waitForPresentAndVisible();
        sel.type(locator, txt);
    }

    public void submit() {
        waitForPresentAndVisible();
        sel.getDriver().findElement((By) locator).submit();
    }
}