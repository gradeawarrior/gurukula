package com.gurukula.ui.pagemodel.webelements;

import com.gurukula.ui.Selenium;

public class CheckBox extends Element{

    public CheckBox(Selenium sel, Object locator) {
        super(sel, locator);
    }

    public void click() {
        waitForPresentAndVisible();
        sel.click(locator);
    }

    public boolean isSelected() {
        waitForPresentAndVisible();
        return sel.isSelected(locator);
    }
}
