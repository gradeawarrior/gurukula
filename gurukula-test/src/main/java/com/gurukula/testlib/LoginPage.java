package com.gurukula.testlib;

import com.github.seleniumpm.WebPage;
import com.github.seleniumpm.webelements.Button;
import com.github.seleniumpm.webelements.Checkbox;
import com.github.seleniumpm.webelements.TextElement;
import com.github.seleniumpm.webelements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class LoginPage extends GurukulaPage {
    public TextElement loginError;
    public TextField loginTextField;
    public TextField passwordTextField;
    public Checkbox automaticLoginCheckBox;
    public Button submitButton;

    public LoginPage(WebDriver driver, URI server) {
        super(driver, server);
        path = "/#/login";
        loginError = new TextElement(driver, By.xpath("//div[@translate='login.messages.error.authentication']/strong"));
        loginTextField = new TextField(driver, By.id("username"));
        passwordTextField = new TextField(driver, By.id("password"));
        automaticLoginCheckBox = new Checkbox(driver, By.id("rememberMe"));
        submitButton = new Button(driver, By.xpath("//button[@type='submit']"));
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
        super.waitForPageLoad();
        loginTextField.waitForPresentAndVisible(pageWaitTime);
        passwordTextField.waitForPresentAndVisible(pageWaitTime);
        automaticLoginCheckBox.waitForPresentAndVisible(pageWaitTime);
        submitButton.waitForPresentAndVisible(pageWaitTime);
        headerWidget.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        super.validate();
        loginTextField.presentAndVisible();
        passwordTextField.presentAndVisible();
        automaticLoginCheckBox.presentAndVisible();
        submitButton.presentAndVisible();
        headerWidget.presentAndVisible();
    }
}
