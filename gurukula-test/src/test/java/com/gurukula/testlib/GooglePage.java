package com.gurukula.testlib;

import com.gurukula.ui.pagemodel.WebPage;
import com.gurukula.ui.pagemodel.webelements.TextField;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage extends WebPage {
    public TextField searchField;

    public GooglePage(WebDriver driver) {
        super(driver, "http://www.google.com");
        searchField = new TextField(driver, By.name("q"));
    }

    public void waitForPageLoad() {
        throw new NotImplementedException("This isn't implemented yet!");
    }

    public void validate() {
        throw new NotImplementedException("This isn't implemented yet!");
    }
}
