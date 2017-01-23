package com.gurukula.testlib;

import com.github.seleniumpm.WebPage;
import com.github.seleniumpm.webelements.Button;
import com.github.seleniumpm.webelements.Dropdown;
import com.github.seleniumpm.webelements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class SettingsPage extends GurukulaPage {
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField emailTextField;
    public Dropdown languageDropDown;
    public Button saveButton;

    public SettingsPage(WebDriver driver, URI server) {
        super(driver, server);
        path = "/#/settings";
        firstNameTextField = new TextField(driver, By.name("firstName"));
        lastNameTextField = new TextField(driver, By.name("lastName"));
        emailTextField = new TextField(driver, By.name("email"));
        languageDropDown = new Dropdown(driver, By.name("langKey"));
        saveButton = new Button(driver, By.xpath("//button[@translate='settings.form.button']"));
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
