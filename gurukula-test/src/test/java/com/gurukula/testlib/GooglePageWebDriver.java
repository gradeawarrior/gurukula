package com.gurukula.testlib;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;
import com.gurukula.ui.pagemodel.webelements.TextField;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;

public class GooglePageWebDriver extends WebPage {
    public TextField searchField;

    public GooglePageWebDriver(Selenium sel) {
        super(sel, "http://www.google.com");
        searchField = new TextField(sel, By.name("q"));
    }

    public GooglePageWebDriver(Selenium sel, String url) {
        super(sel, url);
        searchField = new TextField(sel, By.name("q"));
    }

    public WebPage waitForPageLoad() {
        throw new NotImplementedException("This isn't implemented yet!");
    }

    public void validate() {
        searchField.waitForPresentAndVisible();
    }
}
