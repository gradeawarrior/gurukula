package com.gurukula.testlib;

import com.github.seleniumpm.webelements.Button;
import com.github.seleniumpm.webelements.TextElement;
import com.github.seleniumpm.webelements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class RegistrationPage extends GurukulaPage {
    public TextElement errorMessage;
    public TextElement minPasswordMessage;
    public TextElement invalidEmailMessage;
    public TextElement minCharacterEmailMessage;

    public TextField loginField;
    public TextField emailField;
    public TextField passwordField;
    public TextField passwordConfirmField;
    public Button registerButton;

    public RegistrationPage(WebDriver driver, URI server) {
        super(driver, server);
        path = "/#/register";

        errorMessage = new TextElement(driver, By.xpath("//div[@translate='register.messages.error.fail']/strong"));
        minPasswordMessage = new TextElement(driver, By.xpath("//p[@translate='global.messages.validate.newpassword.minlength']"));
        invalidEmailMessage = new TextElement(driver, By.xpath("//p[@translate='global.messages.validate.email.invalid']"));
        minCharacterEmailMessage = new TextElement(driver, By.xpath("//p[@translate='global.messages.validate.email.minlength']"));

        loginField = new TextField(driver, By.name("login"));
        emailField = new TextField(driver,By.name("email"));
        passwordField = new TextField(driver, By.name("password"));
        passwordConfirmField = new TextField(driver, By.name("confirmPassword"));
        registerButton = new Button(driver, By.xpath("//button[@type='submit']"));
    }

    public RegistrationPage waitForPageLoad() {
        super.waitForPageLoad();
        loginField.waitForPresentAndVisible(pageWaitTime);
        emailField.waitForPresentAndVisible(pageWaitTime);
        passwordField.waitForPresentAndVisible(pageWaitTime);
        passwordConfirmField.waitForPresentAndVisible(pageWaitTime);
        registerButton.waitForPresentAndVisible(pageWaitTime);
        loginWidget.loginLink.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        super.validate();
        headerWidget.validate();
        loginField.presentAndVisible();
        emailField.presentAndVisible();
        passwordField.presentAndVisible();
        passwordConfirmField.presentAndVisible();
        registerButton.presentAndVisible();
        loginWidget.loginLink.presentAndVisible();
    }
}
