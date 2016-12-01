package com.gurukula.testlib.widgets;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.TextField;
import org.openqa.selenium.By;

public class CreateEditBranchWidget extends CreateEditStaffWidget{
    TextField codeField;

    public CreateEditBranchWidget(Selenium sel, Object locator) {
        super(sel, locator);
        codeField = new TextField(sel, By.xpath("//input[@name='code']"));
    }
}
