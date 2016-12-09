package com.gurukula.testlib.widgets;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.Button;
import com.gurukula.ui.pagemodel.webelements.DropDown;
import com.gurukula.ui.pagemodel.webelements.TextField;
import com.gurukula.ui.pagemodel.webelements.Widget;
import org.openqa.selenium.By;

public class CreateEditStaffWidget extends Widget {
    public TextField idField;
    public TextField nameField;
    public DropDown branchDropdown;
    public Button cancelButton;
    public Button saveButton;
    public Button closeButton;

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
        super.waitForPresentAndVisible(sel.getPageTimeout());
        idField.waitForPresentAndVisible(sel.getPageTimeout());
        nameField.waitForPresentAndVisible(sel.getPageTimeout());
        branchDropdown.waitForPresentAndVisible(sel.getPageTimeout());
        // cancelButton.waitForPresentAndVisible(sel.getPageTimeout());
        saveButton.waitForPresentAndVisible(sel.getPageTimeout());
        closeButton.waitForPresentAndVisible(sel.getPageTimeout());
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
