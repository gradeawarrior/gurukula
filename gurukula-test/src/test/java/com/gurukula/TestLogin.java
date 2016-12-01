package com.gurukula;

import com.google.common.base.Preconditions;
import com.gurukula.testlib.HomePageAuthenticated;
import com.gurukula.testlib.HomePageUnauthenticated;
import com.gurukula.testlib.LoginPage;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.SeleniumWebdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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

        Preconditions.checkNotNull(gurukulaURL, "Gurukula URL is not null!");
        Preconditions.checkArgument(gurukulaURL.length() > 0, "Gurukula URL is not empty!");
        try {
            // Specifying where the tests will run will be based on URL
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("firefox");
            WebDriver browser = new RemoteWebDriver(new URL(server), capabilities);
            sel = new SeleniumWebdriver(browser);

            // Instantiate all pages that will be visited
            homePageAuthenticated = new HomePageAuthenticated(sel);
            homePageUnauthenticated = new HomePageUnauthenticated(sel);
            loginPage = new LoginPage(sel);

            // Open browser to the homepage
            homePageUnauthenticated.open(gurukulaURL);
        } catch (Exception e){
            if (sel != null)
                sel.quit();
            throw new Exception("Something went wrong!");
        }
    }

    public void testUnauthenticated() {
        homePageUnauthenticated.open(gurukulaURL);
        homePageUnauthenticated.waitForPageLoad().validate();
    }

    public void testAuthenticated() {
        homePageUnauthenticated.open(gurukulaURL);
        homePageUnauthenticated.waitForPageLoad().validate();
        homePageUnauthenticated.loginWidget.loginLink.click();
        loginPage.waitForPageLoad().validate();
        loginPage.login("admin", "admin");
        homePageAuthenticated.waitForPageLoad().validate();
        Assert.assertEquals(homePageAuthenticated.isLoggedIn(), true, "Expecting to be logged-in!");
    }

    public void testLogout() {
        // Login
        homePageUnauthenticated.open(gurukulaURL);
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
    public void methodTearDown() {
        homePageAuthenticated.logout();
        homePageUnauthenticated.waitForPageLoad();
    }

    @AfterClass
    public void classTearDown() {
        if (sel != null)
            sel.quit();
    }
}
