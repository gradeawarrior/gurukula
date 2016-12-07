package com.gurukula.testlib;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.Button;
import com.gurukula.ui.pagemodel.webelements.TextElement;
import com.gurukula.ui.pagemodel.webelements.TextField;
import org.openqa.selenium.By;

public class RegistrationPage extends GurukulaPage {
    TextElement errorMessage;
    TextElement minPasswordMessage;
    TextElement invalidEmailMessage;
    TextElement minCharacterEmailMessage;

    TextField loginField;
    TextField emailField;
    TextField passwordField;
    TextField passwordConfirmField;
    Button registerButton;

    public RegistrationPage(Selenium sel) {
        super(sel);
        path = "/#/register";
        setWebElements();
    }

    protected void setWebElements() {
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
        loginField.waitForPresentAndVisible();
        emailField.waitForPresentAndVisible();
        passwordField.waitForPresentAndVisible();
        passwordConfirmField.waitForPresentAndVisible();
        registerButton.waitForPresentAndVisible();
        loginWidget.loginLink.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        headerWidget.validate();
        loginField.presentAndVisible();
        emailField.presentAndVisible();
        passwordField.presentAndVisible();
        passwordConfirmField.presentAndVisible();
        registerButton.presentAndVisible();
        loginWidget.loginLink.presentAndVisible();
    }
}
