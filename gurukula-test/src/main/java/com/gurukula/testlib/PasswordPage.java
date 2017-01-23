package com.gurukula.testlib;

import com.github.seleniumpm.webelements.Button;
import com.github.seleniumpm.webelements.TextElement;
import com.github.seleniumpm.webelements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class PasswordPage extends GurukulaPage {
    public TextElement errorMessage;
    public TextField passwordField;
    public TextField passwordConfirmField;
    public Button saveButton;

    public PasswordPage(WebDriver driver, URI server) {
        super(driver, server);
        path = "/#/password";
        errorMessage = new TextElement(driver, By.xpath("//div[@translate='password.messages.error']/strong"));
        passwordField = new TextField(driver, By.name("password"));
        passwordConfirmField = new TextField(driver, By.name("confirmPassword"));
        saveButton = new Button(driver, By.xpath("//button[@type='submit']"));
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
