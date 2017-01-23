package com.gurukula.testlib.widgets;

import com.github.seleniumpm.webelements.Button;
import com.github.seleniumpm.webelements.Dropdown;
import com.github.seleniumpm.webelements.TextField;
import com.github.seleniumpm.webelements.Widget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateEditStaffWidget extends Widget {
    public TextField idField;
    public TextField nameField;
    public Dropdown branchDropdown;
    public Button cancelButton;
    public Button saveButton;
    public Button closeButton;

    public CreateEditStaffWidget(WebDriver driver, By locator) {
        super(driver, locator);
        idField = new TextField(driver, By.xpath("//input[@name='id']"));
        nameField = new TextField(driver, By.xpath("//input[@name='name']"));
        branchDropdown = new Dropdown(driver, By.xpath("//select[@name='related_branch']"));
        cancelButton = new Button(driver, By.xpath("//button/span[@translate='entity.action.close']"));
        saveButton = new Button(driver, By.xpath("//button/span[@translate='entity.action.save']"));
        closeButton = new Button(driver, By.xpath("//button[@class='close']"));
    }

    public CreateEditStaffWidget waitForPresentAndVisible() {
        super.waitForPresentAndVisible(pageWaitTime);
        idField.waitForPresentAndVisible(pageWaitTime);
        nameField.waitForPresentAndVisible(pageWaitTime);
        branchDropdown.waitForPresentAndVisible(pageWaitTime);
        // cancelButton.waitForPresentAndVisible(pageWaitTime);
        saveButton.waitForPresentAndVisible(pageWaitTime);
        closeButton.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        super.presentAndVisible();
        idField.presentAndVisible();
        nameField.presentAndVisible();
        branchDropdown.presentAndVisible();
        // cancelButton.presentAndVisible();
        saveButton.presentAndVisible();
        closeButton.presentAndVisible();
    }
}
