package com.gurukula;

import com.google.common.base.Preconditions;
import com.gurukula.testlib.*;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.SeleniumWebdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TestRegistration {
    static Selenium sel = null;
    static String gurukulaURL = "http://localhost:8080";

    static HomePageUnauthenticated homePageUnauthenticated = null;
    static HomePageAuthenticated homePageAuthenticated = null;
    static RegistrationPage registrationPage = null;

    @BeforeClass
    public void classSetUp() throws Exception {
        String server = System.getProperty("selenium.server", "http://localhost:4444") + "/wd/hub";
        gurukulaURL = System.getProperty("gurukula.url", "http://localhost:8080");
        String browserType = System.getProperty("browser.type", "firefox");

        Preconditions.checkNotNull(gurukulaURL, "Gurukula URL is not null!");
        Preconditions.checkArgument(gurukulaURL.length() > 0, "Gurukula URL is not empty!");
        try {
            // Specifying where the tests will run will be based on URL
            WebDriver browser;
            if (browserType.equals("firefox")) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("firefox");
                browser = new RemoteWebDriver(new URL(server), capabilities);
            } else if (browserType.equals("headless")) {
                browser = new HtmlUnitDriver();
            } else {
                throw new UnsupportedOperationException("Cannot launch browserType: '" + browserType + "'!");
            }

            // Instantiate Selenium
            sel = new SeleniumWebdriver(browser, new URI(gurukulaURL));

            // Instantiate all pages that will be visited
            homePageAuthenticated = new HomePageAuthenticated(sel);
            homePageUnauthenticated = new HomePageUnauthenticated(sel);
            registrationPage = new RegistrationPage(sel);
        } catch (Exception e) {
            if (sel != null)
                sel.quit();
            throw e;
        }
    }

    @Test
    public void testRegisterUser() throws URISyntaxException {
        registrationPage.open();
        registrationPage.waitForPageLoad().validate();
        registrationPage.loginField.clear().type("user1");
        registrationPage.emailField.clear().type("user1@example.com");
        registrationPage.passwordField.clear().type("12345");
        registrationPage.passwordConfirmField.clear().type("12345");
        registrationPage.registerButton.click();
        Assert.assertEquals(registrationPage.errorMessage.isPresent(), false, "There should not be a registration error");
    }

    @Test
    public void testRegisterUserMissingLogin() throws URISyntaxException {
        registrationPage.open();
        registrationPage.waitForPageLoad().validate();
        registrationPage.loginField.clear();
        registrationPage.emailField.clear().type("user1@example.com");
        registrationPage.passwordField.clear().type("12345");
        registrationPage.passwordConfirmField.clear().type("12345");
        Assert.assertEquals(registrationPage.registerButton.isEnabled(), false, "Expecting register button to be disabled");
    }

    @Test
    public void testRegisterUserMissingEmail() throws URISyntaxException {
        registrationPage.open();
        registrationPage.waitForPageLoad().validate();
        registrationPage.loginField.clear().type("user1");
        registrationPage.emailField.clear();
        registrationPage.passwordField.clear().type("12345");
        registrationPage.passwordConfirmField.clear().type("12345");
        Assert.assertEquals(registrationPage.registerButton.isEnabled(), false, "Expecting register button to be disabled");
    }

    @Test
    public void testRegisterUserMissingPassword() throws URISyntaxException {
        registrationPage.open();
        registrationPage.waitForPageLoad().validate();
        registrationPage.loginField.clear().type("user1");
        registrationPage.emailField.clear().type("user1@example.com");
        registrationPage.passwordField.clear();
        registrationPage.passwordConfirmField.clear().type("12345");
        Assert.assertEquals(registrationPage.registerButton.isEnabled(), false, "Expecting register button to be disabled");
    }

    @Test
    public void testRegisterUserMissingPasswordConfirm() throws URISyntaxException {
        registrationPage.open();
        registrationPage.waitForPageLoad().validate();
        registrationPage.loginField.clear().type("user1");
        registrationPage.emailField.clear().type("user1@example.com");
        registrationPage.passwordField.clear().type("12345");
        registrationPage.passwordConfirmField.clear();
        Assert.assertEquals(registrationPage.registerButton.isEnabled(), false, "Expecting register button to be disabled");
    }

    @Test
    public void testRegisterUserShortPassword() throws URISyntaxException {
        registrationPage.open();
        registrationPage.waitForPageLoad().validate();
        registrationPage.loginField.clear().type("user1");
        registrationPage.emailField.clear().type("user1@example.com");
        registrationPage.passwordField.clear().type("1234");
        registrationPage.passwordConfirmField.clear().type("1234");
        Assert.assertEquals(registrationPage.registerButton.isEnabled(), false, "Expecting register button to be disabled");
    }

    @DataProvider
    public static final Object[][] getInvalidEmails() {
        return new Object[][]{
                {"user1"},
                {"user1@"},
                {"user1@foobar."},
                {"@example"}

        };
    }

    @Test(dataProvider = "getInvalidEmails")
    public void testInvalidEmails(String email) throws URISyntaxException {
        registrationPage.open();
        registrationPage.waitForPageLoad().validate();
        registrationPage.loginField.clear().type("user1");
        registrationPage.emailField.clear().type(email);
        registrationPage.passwordField.clear().type("1234");
        registrationPage.passwordConfirmField.clear().type("1234");
        Assert.assertEquals(registrationPage.registerButton.isEnabled(), false, "Expecting register button to be disabled");
    }

    @AfterClass
    public void classTearDown() {
        if (sel != null)
            sel.quit();
    }
}
