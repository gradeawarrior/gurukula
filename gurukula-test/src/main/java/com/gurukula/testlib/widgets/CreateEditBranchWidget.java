package com.gurukula.testlib.widgets;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.TextField;
import org.openqa.selenium.By;

public class CreateEditBranchWidget extends CreateEditStaffWidget{
    public TextField codeField;

    public CreateEditBranchWidget(Selenium sel, Object locator) {
        super(sel, locator);
        codeField = new TextField(sel, By.xpath("//input[@name='code']"));
    }

    public CreateEditBranchWidget waitForPresentAndVisible() {
        super.waitForPresentAndVisible(sel.getPageTimeout());
        idField.waitForPresentAndVisible(sel.getPageTimeout());
        nameField.waitForPresentAndVisible(sel.getPageTimeout());
        codeField.waitForPresentAndVisible(sel.getPageTimeout());
        saveButton.waitForPresentAndVisible(sel.getPageTimeout());
        closeButton.waitForPresentAndVisible(sel.getPageTimeout());
        return this;
    }
}
