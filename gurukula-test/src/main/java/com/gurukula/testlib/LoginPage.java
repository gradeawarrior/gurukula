package com.gurukula.testlib;

import com.gurukula.testlib.GurukulaPage;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;
import com.gurukula.ui.pagemodel.webelements.Button;
import com.gurukula.ui.pagemodel.webelements.CheckBox;
import com.gurukula.ui.pagemodel.webelements.TextElement;
import com.gurukula.ui.pagemodel.webelements.TextField;
import org.openqa.selenium.By;

public class LoginPage extends GurukulaPage {
    public TextElement loginError;
    public TextField loginTextField;
    public TextField passwordTextField;
    public CheckBox automaticLoginCheckBox;
    public Button submitButton;

    public LoginPage(Selenium sel) {
        super(sel);
        path = "/#/login";
        loginError = new TextElement(sel, By.xpath("//div[@translate='login.messages.error.authentication']/strong"));
        loginTextField = new TextField(sel, By.id("username"));
        passwordTextField = new TextField(sel, By.id("password"));
        automaticLoginCheckBox = new CheckBox(sel, By.id("rememberMe"));
        submitButton = new Button(sel, By.xpath("//button[@type='submit']"));
    }

    public GurukulaPage login(String username, String password) {
        loginTextField.clear().type(username);
        passwordTextField.clear().type(password);
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
        // TODO - Adding a little sleep to help things along when waiting for elements to show up on the page
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
