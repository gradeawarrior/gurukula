package com.gurukula.poc.testlib;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;
import com.gurukula.ui.pagemodel.webelements.TextField;

public class GooglePage10 extends WebPage {
    public TextField searchField;

    public GooglePage10(Selenium sel) {
        super(sel, "http://www.google.com");
        searchField = new TextField(sel, "q");
    }

    public GooglePage10(Selenium sel, String path) {
        super(sel, path);
        searchField = new TextField(sel, "q");
    }

    public WebPage waitForPageLoad() {
        sel.waitForPageToLoad();
        return this;
    }

    public void validate() {
        searchField.waitForPresentAndVisible();
    }
}
