package com.gurukula.ui.pagemodel.webelements;

import com.gurukula.ui.Selenium;

public class TextElement extends Element {

    public TextElement(Selenium sel, Object locator) {
        super(sel, locator);
    }

    public String getText() {
        return sel.getText(locator);
    }
}