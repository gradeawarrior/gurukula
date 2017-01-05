package com.gurukula.testlib;

import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.pagemodel.WebPage;
import com.github.seleniumpm.pagemodel.webelements.Button;
import com.github.seleniumpm.pagemodel.webelements.DropDown;
import com.github.seleniumpm.pagemodel.webelements.TextField;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;

public class SettingsPage extends GurukulaPage {
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField emailTextField;
    public DropDown languageDropDown;
    public Button saveButton;

    public SettingsPage(Selenium sel) {
        super(sel);
        path = "/#/settings";
        firstNameTextField = new TextField(sel, By.name("firstName"));
        lastNameTextField = new TextField(sel, By.name("lastName"));
        emailTextField = new TextField(sel, By.name("email"));
        languageDropDown = new DropDown(sel, By.name("langKey"));
        saveButton = new Button(sel, By.xpath("//button[@translate='settings.form.button']"));
    }

    public WebPage waitForPageLoad() {
        super.waitForPageLoad();
        firstNameTextField.waitForPresentAndVisible(pageWaitTime);
        lastNameTextField.waitForPresentAndVisible(pageWaitTime);
        emailTextField.waitForPresentAndVisible(pageWaitTime);
        languageDropDown.waitForPresentAndVisible(pageWaitTime);
        saveButton.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        super.validate();
        firstNameTextField.presentAndVisible();
        lastNameTextField.presentAndVisible();
        emailTextField.presentAndVisible();
        languageDropDown.presentAndVisible();
        saveButton.presentAndVisible();
    }
}
