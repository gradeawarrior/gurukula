package com.gurukula.ui.pagemodel.webelements;

import com.gurukula.ui.Selenium;

public class Link extends Element {

    public Link(Selenium sel, Object locator) {
        super(sel, locator);
    }

    public void click() {
        click(true);
    }

    public void click(boolean checkVisible) {
        if (checkVisible) {
            waitForPresentAndVisible();
        } else {
            waitForPresent();
        }
        sel.click(locator);
    }
}
