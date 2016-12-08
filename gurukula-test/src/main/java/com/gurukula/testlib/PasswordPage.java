package com.gurukula.testlib;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.Button;
import com.gurukula.ui.pagemodel.webelements.TextElement;
import com.gurukula.ui.pagemodel.webelements.TextField;
import org.openqa.selenium.By;

public class PasswordPage extends GurukulaPage {
    public TextElement errorMessage;
    public TextField passwordField;
    public TextField passwordConfirmField;
    public Button saveButton;

    public PasswordPage(Selenium sel) {
        super(sel);
        path = "/#/password";
        errorMessage = new TextElement(sel, By.xpath("//div[@translate='password.messages.error']/strong"));
        passwordField = new TextField(sel, By.name("password"));
        passwordConfirmField = new TextField(sel, By.name("confirmPassword"));
        saveButton = new Button(sel, By.xpath("//button[@type='submit']"));
    }

    public PasswordPage waitForPageLoad() {
        super.waitForPageLoad();
        passwordField.waitForPresent(pageWaitTime);
        passwordConfirmField.waitForPresent(pageWaitTime);
        saveButton.waitForPresent(pageWaitTime);
        return this;
    }

    public void validate() {
        super.validate();
        passwordField.presentAndVisible();
        passwordConfirmField.presentAndVisible();
        saveButton.presentAndVisible();
    }
}
