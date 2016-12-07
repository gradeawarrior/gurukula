package com.gurukula.testlib;

import com.gurukula.testlib.widgets.CreateEditStaffWidget;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;
import com.gurukula.ui.pagemodel.webelements.Button;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;

public class StaffsPage extends GurukulaPage {
    Button createStaffButton;
    CreateEditStaffWidget createEditStaffWidget;

    public StaffsPage(Selenium sel) {
        super(sel);
        path = "/#/staff";
        createStaffButton = new Button(sel, By.xpath("//button/span[@translate='gurukulaApp.staff.home.createLabel']"));
        createEditStaffWidget = new CreateEditStaffWidget(sel, By.name("editForm"));
    }

    public WebPage waitForPageLoad() {
        super.waitForPageLoad();
        createStaffButton.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        super.validate();
        createStaffButton.presentAndVisible();
    }
}
