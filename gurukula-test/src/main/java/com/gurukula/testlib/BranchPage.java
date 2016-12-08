package com.gurukula.testlib;

import com.gurukula.testlib.widgets.CreateEditBranchWidget;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;
import com.gurukula.ui.pagemodel.webelements.Button;
import com.gurukula.ui.pagemodel.webelements.Table;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;

public class BranchPage extends GurukulaPage {
    public Button createBranchButton;
    public CreateEditBranchWidget createEditBranchWidget;
    public Table branchesTable;

    public BranchPage(Selenium sel) {
        super(sel);
        path = "/#/branch";
        createBranchButton = new Button(sel, By.xpath("//button/span[@translate='gurukulaApp.branch.home.createLabel']"));
        createEditBranchWidget = new CreateEditBranchWidget(sel, By.name("editForm"));
        branchesTable = new Table(sel, By.xpath("//div[contains(@class, 'table-responsive')]/table"));
    }

    public WebPage waitForPageLoad() {
        super.waitForPageLoad();
        createBranchButton.waitForPresentAndVisible(pageWaitTime);
        branchesTable.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        super.validate();
        createBranchButton.presentAndVisible();
        branchesTable.presentAndVisible();
    }
}
