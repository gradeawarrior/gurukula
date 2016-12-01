package com.gurukula.testlib;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;
import org.apache.commons.lang.NotImplementedException;

public class SessionsPage extends WebPage {

    public SessionsPage(Selenium sel) {
        super(sel);
    }

    public WebPage waitForPageLoad() {
        throw new NotImplementedException();
    }

    public void validate() {
        throw new NotImplementedException();
    }
}
