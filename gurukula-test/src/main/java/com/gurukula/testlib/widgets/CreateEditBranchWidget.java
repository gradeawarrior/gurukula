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
        return waitForPresentAndVisible(sel.getElementTimeout());
    }

    public CreateEditBranchWidget waitForPresentAndVisible(long waitTime) {
        super.waitForPresentAndVisible(waitTime);
        nameField.waitForPresentAndVisible(waitTime);
        codeField.waitForPresentAndVisible(waitTime);
        return this;
    }
}
