package com.gurukula.testlib;

import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.pagemodel.webelements.Button;
import com.github.seleniumpm.pagemodel.webelements.TextElement;
import com.github.seleniumpm.pagemodel.webelements.TextField;
import org.openqa.selenium.By;

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

    public RegistrationPage(Selenium sel) {
        super(sel);
        path = "/#/register";

        errorMessage = new TextElement(sel, By.xpath("//div[@translate='register.messages.error.fail']/strong"));
        minPasswordMessage = new TextElement(sel, By.xpath("//p[@translate='global.messages.validate.newpassword.minlength']"));
        invalidEmailMessage = new TextElement(sel, By.xpath("//p[@translate='global.messages.validate.email.invalid']"));
        minCharacterEmailMessage = new TextElement(sel, By.xpath("//p[@translate='global.messages.validate.email.minlength']"));

        loginField = new TextField(sel, By.name("login"));
        emailField = new TextField(sel,By.name("email"));
        passwordField = new TextField(sel, By.name("password"));
        passwordConfirmField = new TextField(sel, By.name("confirmPassword"));
        registerButton = new Button(sel, By.xpath("//button[@type='submit']"));
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
