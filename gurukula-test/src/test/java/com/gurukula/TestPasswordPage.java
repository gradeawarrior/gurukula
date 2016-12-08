package com.gurukula;

import com.google.common.base.Preconditions;
import com.gurukula.testlib.HomePageAuthenticated;
import com.gurukula.testlib.LoginPage;
import com.gurukula.testlib.PasswordPage;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.SeleniumWebdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Test
public class TestPasswordPage {
    static Selenium sel = null;
    static String gurukulaURL = "http://localhost:8080";

    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;
    static PasswordPage passwordPage = null;

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
            loginPage = new LoginPage(sel);
            passwordPage = new PasswordPage(sel);

            // Login
            loginPage.open();
            loginPage.login("admin", "admin");
            homePageAuthenticated.waitForPageLoad().validate();
        } catch (Exception e) {
            if (sel != null)
                sel.quit();
            throw e;
        }
    }

    public void testUpdatePassword() throws URISyntaxException {
        passwordPage.open();
        passwordPage.waitForPageLoad().validate();
        passwordPage.passwordField.clear().type("admin1!");
        passwordPage.passwordConfirmField.clear().type("admin1!");
        passwordPage.saveButton.click();
        Assert.assertEquals(passwordPage.errorMessage.isPresent(), false, "There should be no save error!");
    }

    public void testNoPasswordEntered() throws URISyntaxException {
        passwordPage.open();
        passwordPage.waitForPageLoad().validate();
        passwordPage.passwordField.clear();
        passwordPage.passwordConfirmField.clear();
        Assert.assertEquals(passwordPage.saveButton.isEnabled(), false, "Expecting save button to be disabled!");
    }

    public void testMissingPassword() throws URISyntaxException {
        passwordPage.open();
        passwordPage.waitForPageLoad().validate();
        passwordPage.passwordField.clear();
        passwordPage.passwordConfirmField.clear().type("admin1!");
        Assert.assertEquals(passwordPage.saveButton.isEnabled(), false, "Expecting save button to be disabled!");
    }

    public void testMissingPasswordConfirm() throws URISyntaxException {
        passwordPage.open();
        passwordPage.waitForPageLoad().validate();
        passwordPage.passwordField.clear().type("admin1!");
        passwordPage.passwordConfirmField.clear();
        Assert.assertEquals(passwordPage.saveButton.isEnabled(), false, "Expecting save button to be disabled!");
    }

    @AfterClass
    public void classTearDown() {
        if (sel != null)
            sel.quit();
    }
}
