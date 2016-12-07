package com.gurukula.testlib;

import com.gurukula.testlib.widgets.CreateEditBranchWidget;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;
import com.gurukula.ui.pagemodel.webelements.Button;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;

public class BranchPage extends GurukulaPage {
    Button createBranchButton;
    CreateEditBranchWidget createEditBranchWidget;

    public BranchPage(Selenium sel) {
        super(sel);
        path = "/#/branch";
        createBranchButton = new Button(sel, By.xpath("//button/span[@translate='gurukulaApp.branch.home.createLabel']"));
        createEditBranchWidget = new CreateEditBranchWidget(sel, By.name("editForm"));
    }

    public WebPage waitForPageLoad() {
        super.waitForPageLoad();
        createBranchButton.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        super.validate();
        createBranchButton.presentAndVisible();
    }
}
