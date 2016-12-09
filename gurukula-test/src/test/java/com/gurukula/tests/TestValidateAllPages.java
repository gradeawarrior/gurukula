package com.gurukula.tests;

import com.gurukula.GurukulaTest;
import com.gurukula.testlib.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class TestValidateAllPages extends GurukulaTest {
    static HomePageUnauthenticated homePageUnauthenticated = null;
    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;
    static BranchPage branchPage = null;
    static StaffsPage staffsPage = null;
    static RegistrationPage registrationPage = null;
    static SessionsPage sessionsPage = null;
    static SettingsPage settingsPage = null;
    static PasswordPage passwordPage = null;

    @BeforeClass
    public void classSetUp() throws Exception {
        try {
            // Instantiate all pages that will be visited
            homePageAuthenticated = new HomePageAuthenticated(sel);
            homePageUnauthenticated = new HomePageUnauthenticated(sel);
            loginPage = new LoginPage(sel);
            branchPage = new BranchPage(sel);
            staffsPage = new StaffsPage(sel);
            registrationPage = new RegistrationPage(sel);
            sessionsPage = new SessionsPage(sel);
            settingsPage = new SettingsPage(sel);
            passwordPage = new PasswordPage(sel);
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

    @Test(dependsOnGroups = "authenticated")
    public void testPasswordPage() throws URISyntaxException {
        passwordPage.open();
        passwordPage.waitForPageLoad().validate();
    }
}
