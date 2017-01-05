package com.gurukula.testlib;

import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.pagemodel.WebPage;
import com.github.seleniumpm.pagemodel.webelements.Table;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;

public class SessionsPage extends GurukulaPage{
    public Table activeSessionsTable;

    public SessionsPage(Selenium sel) {
        super(sel);
        path = "/#/sessions";
        activeSessionsTable = new Table(sel, By.xpath("//div[contains(@class, 'table-responsive')]/table"));
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
