package com.gurukula.testlib.widgets;

import com.github.seleniumpm.webelements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateEditBranchWidget extends CreateEditStaffWidget{
    public TextField codeField;

    public CreateEditBranchWidget(WebDriver driver, By locator) {
        super(driver, locator);
        codeField = new TextField(driver, By.xpath("//input[@name='code']"));
    }

    public CreateEditBranchWidget waitForPresentAndVisible() {
        super.waitForPresentAndVisible(pageWaitTime);
        idField.waitForPresentAndVisible(pageWaitTime);
        nameField.waitForPresentAndVisible(pageWaitTime);
        codeField.waitForPresentAndVisible(pageWaitTime);
        saveButton.waitForPresentAndVisible(pageWaitTime);
        closeButton.waitForPresentAndVisible(pageWaitTime);
        return this;
    }
}
