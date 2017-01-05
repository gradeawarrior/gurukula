package com.gurukula.testlib;

import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.pagemodel.WebPage;
import com.github.seleniumpm.pagemodel.webelements.Button;
import com.github.seleniumpm.pagemodel.webelements.Table;
import com.gurukula.testlib.widgets.CreateEditStaffWidget;
import org.openqa.selenium.By;

public class StaffsPage extends GurukulaPage {
    public Button createStaffButton;
    public CreateEditStaffWidget createEditStaffWidget;
    public Table staffsTable;

    public StaffsPage(Selenium sel) {
        super(sel);
        path = "/#/staff";
        createStaffButton = new Button(sel, By.xpath("//button/span[@translate='gurukulaApp.staff.home.createLabel']"));
        createEditStaffWidget = new CreateEditStaffWidget(sel, By.name("editForm"));
        staffsTable = new Table(sel, By.xpath("//div[contains(@class, 'table-responsive')]/table"));
    }

    public WebPage waitForPageLoad() {
        super.waitForPageLoad();
        createStaffButton.waitForPresentAndVisible(pageWaitTime);
        staffsTable.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        super.validate();
        createStaffButton.presentAndVisible();
        staffsTable.presentAndVisible();
    }
}
