package com.gurukula.testlib;

import com.github.seleniumpm.WebPage;
import com.github.seleniumpm.webelements.Button;
import com.github.seleniumpm.webelements.Table;
import com.gurukula.testlib.widgets.CreateEditStaffWidget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class StaffsPage extends GurukulaPage {
    public Button createStaffButton;
    public CreateEditStaffWidget createEditStaffWidget;
    public Table staffsTable;

    public StaffsPage(WebDriver driver, URI server) {
        super(driver, server);
        path = "/#/staff";
        createStaffButton = new Button(driver, By.xpath("//button/span[@translate='gurukulaApp.staff.home.createLabel']"));
        createEditStaffWidget = new CreateEditStaffWidget(driver, By.name("editForm"));
        staffsTable = new Table(driver, By.xpath("//div[contains(@class, 'table-responsive')]/table"));
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
