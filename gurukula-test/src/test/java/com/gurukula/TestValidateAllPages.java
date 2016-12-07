package com.gurukula;

import com.google.common.base.Preconditions;
import com.gurukula.testlib.*;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.SeleniumWebdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TestValidateAllPages {
    static Selenium sel = null;
    static String gurukulaURL = "http://localhost:8080";

    static HomePageUnauthenticated homePageUnauthenticated = null;
    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;
    static BranchPage branchPage = null;
    static StaffsPage staffsPage = null;
    static RegistrationPage registrationPage = null;
    static SessionsPage sessionsPage = null;
    static SettingsPage settingsPage = null;

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
            loginPage = new LoginPage(sel);
            branchPage = new BranchPage(sel);
            staffsPage = new StaffsPage(sel);
            registrationPage = new RegistrationPage(sel);
            sessionsPage = new SessionsPage(sel);
            settingsPage = new SettingsPage(sel);
        } catch (Exception e) {
            if (sel != null)
                sel.quit();
            throw e;
        }
    }

    @Test(groups = "homePageUnAuthenticated")
    public void testHomePageUnauthenticated() throws URISyntaxException {
        homePageUnauthenticated.open();
        homePageUnauthenticated.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "homePageUnAuthenticated")
    public void testRegister() throws URISyntaxException {
        registrationPage.open();
        registrationPage.waitForPageLoad().validate();
    }

    @Test(groups = "loginAuthenticated", dependsOnGroups = "homePageUnAuthenticated")
    public void testLoginPage() throws URISyntaxException {
        loginPage.open();
        loginPage.waitForPageLoad().validate();
    }

    @Test(groups = "authenticated", dependsOnGroups = "loginAuthenticated")
    public void testHomePageAuthenticated() throws URISyntaxException {
        loginPage.open();
        loginPage.waitForPageLoad().validate();
        loginPage.login("admin", "admin");
        homePageAuthenticated.waitForPageLoad().validate();
        homePageAuthenticated.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "authenticated")
    public void testStaffsPage() throws URISyntaxException {
        staffsPage.open();
        staffsPage.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "authenticated")
    public void testBranchesPage() throws URISyntaxException {
        branchPage.open();
        branchPage.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "authenticated")
    public void testSettingsPage() throws URISyntaxException {
        settingsPage.open();
        settingsPage.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "authenticated")
    public void testSessionsPage() throws URISyntaxException {
        sessionsPage.open();
        sessionsPage.waitForPageLoad().validate();
    }

    @AfterClass
    public void classTearDown() {
        if (sel != null)
            sel.quit();
    }
}
