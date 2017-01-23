package com.gurukula.testlib;

import com.github.seleniumpm.webelements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class SessionsPage extends GurukulaPage{
    public Table activeSessionsTable;

    public SessionsPage(WebDriver driver, URI server) {
        super(driver, server);
        path = "/#/sessions";
        activeSessionsTable = new Table(driver, By.xpath("//div[contains(@class, 'table-responsive')]/table"));
    }

    public SessionsPage waitForPageLoad() {
        super.waitForPageLoad();
        activeSessionsTable.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        super.validate();
        activeSessionsTable.presentAndVisible();
    }
}
