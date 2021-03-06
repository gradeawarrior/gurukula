package com.gurukula.tests;

import com.gurukula.GurukulaTest;
import com.gurukula.testlib.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class TestAccessControls extends GurukulaTest {
    static HomePageUnauthenticated homePageUnauthenticated = null;
    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;
    static BranchPage branchPage = null;
    static StaffsPage staffsPage = null;
    static SessionsPage sessionsPage = null;
    static SettingsPage settingsPage = null;
    static PasswordPage passwordPage = null;

    @BeforeClass
    public void classSetUp() throws Exception {
        try {
            // Instantiate all pages that will be visited
            homePageUnauthenticated = new HomePageUnauthenticated(browser, gurukulaURL);
            homePageAuthenticated = new HomePageAuthenticated(browser, gurukulaURL);
            loginPage = new LoginPage(browser, gurukulaURL);
            branchPage = new BranchPage(browser, gurukulaURL);
            staffsPage = new StaffsPage(browser, gurukulaURL);
            sessionsPage = new SessionsPage(browser, gurukulaURL);
            settingsPage = new SettingsPage(browser, gurukulaURL);
            passwordPage = new PasswordPage(browser, gurukulaURL);
        } catch (Exception e) {
            if (browser != null)
                browser.quit();
            throw e;
        }
    }

    @Test(groups = "unauthenticated")
    public void testBranchPageUnauthenticated() throws URISyntaxException {
        branchPage.open();
        loginPage.waitForPageLoad().validate();
    }

    @Test(groups = "unauthenticated")
    public void testStaffsPageUnauthenticated() throws URISyntaxException {
        staffsPage.open();
        loginPage.waitForPageLoad().validate();
    }

    @Test(groups = "unauthenticated")
    public void testSessionsPageUnauthenticated() throws URISyntaxException {
        sessionsPage.open();
        loginPage.waitForPageLoad().validate();
    }

    @Test(groups = "unauthenticated")
    public void testSettingsPageUnauthenticated() throws URISyntaxException {
        settingsPage.open();
        loginPage.waitForPageLoad().validate();
    }

    @Test(groups = "unauthenticated")
    public void testPasswordPageUnauthenticated() throws URISyntaxException {
        passwordPage.open();
        loginPage.waitForPageLoad().validate();
    }

    @Test(groups = "authenticated", dependsOnGroups = "unauthenticated")
    public void login() throws URISyntaxException {
        loginPage.open();
        loginPage.login("admin", "admin");
        homePageAuthenticated.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "authenticated")
    public void testBranchPageAuthenticated() throws URISyntaxException {
        branchPage.open();
        branchPage.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "authenticated")
    public void testStaffsPageAuthenticated() throws URISyntaxException {
        staffsPage.open();
        staffsPage.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "authenticated")
    public void testSessionsPageAuthenticated() throws URISyntaxException {
        sessionsPage.open();
        sessionsPage.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "authenticated")
    public void testSettingsPageAuthenticated() throws URISyntaxException {
        settingsPage.open();
        settingsPage.waitForPageLoad().validate();
    }

    @Test(dependsOnGroups = "authenticated")
    public void testPasswordPageAuthenticated() throws URISyntaxException {
        passwordPage.open();
        passwordPage.waitForPageLoad().validate();
    }
}
