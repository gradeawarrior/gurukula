package com.gurukula.ui.pagemodel.webelements;

import com.gurukula.ui.Selenium;
import org.apache.commons.lang.NotImplementedException;

public class Widget extends Element {

    public Widget(Selenium sel, Object locator) {
        super(sel, locator);
    }

    public void click() {
        waitForPresentAndVisible();
        sel.click(locator);
    }

    public void validate() {
        throw new NotImplementedException();
    }
}
