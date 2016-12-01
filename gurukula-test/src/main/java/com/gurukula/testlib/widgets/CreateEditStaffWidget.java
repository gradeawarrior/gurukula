package com.gurukula.testlib.widgets;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.Button;
import com.gurukula.ui.pagemodel.webelements.DropDown;
import com.gurukula.ui.pagemodel.webelements.TextField;
import com.gurukula.ui.pagemodel.webelements.Widget;
import org.openqa.selenium.By;

public class CreateEditStaffWidget extends Widget {
    TextField idField;
    TextField nameField;
    DropDown branchDropdown;
    Button cancelButton;
    Button saveButton;
    Button closeButton;

    public CreateEditStaffWidget(Selenium sel, Object locator) {
        super(sel, locator);
        idField = new TextField(sel, By.xpath("//input[@name='id']"));
        nameField = new TextField(sel, By.xpath("//input[@name='name']"));
        branchDropdown = new DropDown(sel, By.xpath("//select[@name='related_branch']"));
        cancelButton = new Button(sel, By.xpath("//button/span[@translate='entity.action.close']"));
        saveButton = new Button(sel, By.xpath("//button/span[@translate='entity.action.save']"));
        closeButton = new Button(sel, By.xpath("//button[@class='close']"));
    }

    public CreateEditStaffWidget waitForPresentAndVisible() {
        super.waitForPresentAndVisible();
        idField.waitForPresentAndVisible();
        nameField.waitForPresentAndVisible();
        branchDropdown.waitForPresentAndVisible();
        cancelButton.waitForPresentAndVisible();
        saveButton.waitForPresentAndVisible();
        closeButton.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        super.presentAndVisible();
        idField.presentAndVisible();
        nameField.presentAndVisible();
        branchDropdown.presentAndVisible();
        cancelButton.presentAndVisible();
        saveButton.presentAndVisible();
        closeButton.presentAndVisible();
    }
}