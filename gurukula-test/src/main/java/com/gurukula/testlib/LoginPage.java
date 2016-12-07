package com.gurukula.testlib;

import com.gurukula.testlib.GurukulaPage;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;
import com.gurukula.ui.pagemodel.webelements.Button;
import com.gurukula.ui.pagemodel.webelements.CheckBox;
import com.gurukula.ui.pagemodel.webelements.TextField;
import org.openqa.selenium.By;

public class LoginPage extends GurukulaPage {
    TextField loginTextField;
    TextField passwordTextField;
    CheckBox automaticLoginCheckBox;
    Button submitButton;

    public LoginPage(Selenium sel) {
        super(sel);
        loginTextField = new TextField(sel, By.id("username"));
        passwordTextField = new TextField(sel, By.id("password"));
        automaticLoginCheckBox = new CheckBox(sel, By.id("rememberMe"));
        submitButton = new Button(sel, By.xpath("//button[@type='submit']"));
    }

    public GurukulaPage login(String username, String password) {
        loginTextField.type(username);
        passwordTextField.type(password);
        submitButton.click();
        return this;
    }

    public GurukulaPage loginCondition(String username, String password) {
        if (!isLoggedIn()) {
            loginWidget.loginLink.click();
            waitForPageLoad();
            login(username, password);
        }
        return this;
    }

    public WebPage waitForPageLoad() {
        loginTextField.waitForPresentAndVisible(pageWaitTime);
        passwordTextField.waitForPresentAndVisible(pageWaitTime);
        automaticLoginCheckBox.waitForPresentAndVisible(pageWaitTime);
        submitButton.waitForPresentAndVisible(pageWaitTime);
        headerWidget.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        loginTextField.presentAndVisible();
        passwordTextField.presentAndVisible();
        automaticLoginCheckBox.presentAndVisible();
        submitButton.presentAndVisible();
        headerWidget.presentAndVisible();
    }
}
