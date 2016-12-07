package com.gurukula;

import com.google.common.base.Preconditions;
import com.gurukula.testlib.HomePageAuthenticated;
import com.gurukula.testlib.HomePageUnauthenticated;
import com.gurukula.testlib.LoginPage;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.SeleniumWebdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Test
public class TestLogin {

    static Selenium sel = null;
    static String gurukulaURL = "http://localhost:8080";
    static HomePageUnauthenticated homePageUnauthenticated = null;
    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;

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
        } catch (Exception e) {
            if (sel != null)
                sel.quit();
            throw e;
        }
    }

    @BeforeMethod
    public void methodSetup() throws Exception {
        try {
            homePageUnauthenticated.open(gurukulaURL);
        } catch(Exception e) {
            sel.quit();
            throw e;
        }
    }

    public void testUnauthenticated() {
        homePageUnauthenticated.waitForPageLoad().validate();
    }

    public void testAuthenticated() {
        homePageUnauthenticated.waitForPageLoad().validate();
        homePageUnauthenticated.loginWidget.loginLink.click();
        loginPage.waitForPageLoad().validate();
        loginPage.login("admin", "admin");
        homePageAuthenticated.waitForPageLoad().validate();
        Assert.assertEquals(homePageAuthenticated.isLoggedIn(), true, "Expecting to be logged-in!");
    }

    public void testLogout() {
        // Login
        homePageUnauthenticated.waitForPageLoad().validate();
        homePageUnauthenticated.loginWidget.loginLink.click();
        loginPage.validate();
        loginPage.login("admin", "admin");
        homePageAuthenticated.waitForPageLoad().validate();
        Assert.assertEquals(homePageAuthenticated.isLoggedIn(), true, "Expecting to be logged-in!");

        // Logout
        homePageAuthenticated.headerWidget.accountsWidget.click();
        homePageAuthenticated.headerWidget.accountsWidget.logoutLink.click();
        homePageUnauthenticated.waitForPageLoad().validate();
        Assert.assertEquals(homePageUnauthenticated.isLoggedIn(), false, "Expecting to be logged-out!");
    }

    @AfterMethod
    public void methodTearDown() throws URISyntaxException {
        homePageAuthenticated.logout();
        homePageUnauthenticated.open();
        homePageUnauthenticated.waitForPageLoad();
    }

    @AfterClass
    public void classTearDown() {
        if (sel != null)
            sel.quit();
    }
}
