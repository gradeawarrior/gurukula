package com.gurukula.ui.pagemodel.webelements;

import com.gurukula.ui.Selenium;

public class Button extends Element {

    public Button(Selenium sel, Object locator) {
        super(sel, locator);
    }

    public void click() {
        waitForPresentAndVisible();
        sel.click(locator);
    }

    public boolean isEnabled() {
        waitForPresentAndVisible();
        return sel.isEnabled(locator);
    }
}
