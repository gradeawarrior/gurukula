package com.gurukula.testlib;

import com.github.seleniumpm.WebPage;
import com.github.seleniumpm.webelements.Button;
import com.github.seleniumpm.webelements.Table;
import com.gurukula.testlib.widgets.CreateEditBranchWidget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class BranchPage extends GurukulaPage {
    public Button createBranchButton;
    public CreateEditBranchWidget createEditBranchWidget;
    public Table branchesTable;

    public BranchPage(WebDriver driver, URI server) {
        super(driver, server);
        path = "/#/branch";
        createBranchButton = new Button(driver, By.xpath("//button/span[@translate='gurukulaApp.branch.home.createLabel']"));
        createEditBranchWidget = new CreateEditBranchWidget(driver, By.name("editForm"));
        branchesTable = new Table(driver, By.xpath("//div[contains(@class, 'table-responsive')]/table"));
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
