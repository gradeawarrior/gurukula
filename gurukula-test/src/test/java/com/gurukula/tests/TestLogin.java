package com.gurukula.tests;

import com.gurukula.GurukulaTest;
import com.gurukula.testlib.HomePageAuthenticated;
import com.gurukula.testlib.HomePageUnauthenticated;
import com.gurukula.testlib.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class TestLogin extends GurukulaTest {
    static HomePageUnauthenticated homePageUnauthenticated = null;
    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;

    @BeforeClass
    public void classSetUp() throws Exception {
        try {
            // Instantiate all pages that will be visited
            homePageAuthenticated = new HomePageAuthenticated(sel);
            homePageUnauthenticated = new HomePageUnauthenticated(sel);
            loginPage = new LoginPage(sel);

            // Open Gurukula page
            homePageUnauthenticated.open();
        } catch (Exception e) {
            if (sel != null)
                sel.quit();
            throw e;
        }
    }

    public void login(String username, String password) {
        loginPage.waitForPageLoad().validate();
        loginPage.login(username, password);
        loginPage.waitForPageLoad().validate();
    }

    @Test(groups = "unauthenticated")
    public void testUnauthenticated() {
        homePageUnauthenticated.waitForPageLoad().validate();
    }

    @Test(groups = "authenticated", dependsOnGroups = "unauthenticated")
    public void testAuthenticated() throws InterruptedException {
        homePageUnauthenticated.loginWidget.loginLink.click();
        loginPage.waitForPageLoad().validate();
        loginPage.login("admin", "admin");
        homePageAuthenticated.waitForPageLoad().validate();
        Assert.assertEquals(homePageAuthenticated.isLoggedIn(), true, "Expecting to be logged-in!");
    }

    @Test(groups = "logout", dependsOnGroups = "authenticated")
    public void testLogout() {
        homePageAuthenticated.headerWidget.accountsWidget.click();
        homePageAuthenticated.headerWidget.accountsWidget.logoutLink.click();
        homePageUnauthenticated.waitForPageLoad().validate();
        Assert.assertEquals(homePageUnauthenticated.isLoggedIn(), false, "Expecting to be logged-out!");
    }

    @Test(groups = "loginPageUnauthenticated", dependsOnGroups = "authenticated")
    public void testNonExistentAccount() {
        homePageUnauthenticated.loginWidget.loginLink.click();
        loginPage.waitForPageLoad().validate();
        login("foobar", "admin");
        Assert.assertEquals(loginPage.loginError.isPresentAndVisible(), true, "Expecting a login error");
        Assert.assertEquals(loginPage.loginError.getText(), "Authentication failed!");
    }

    @Test(dependsOnGroups = "loginPageUnauthenticated")
    public void testInvalidPasswordCAPS() {
        login("admin", "ADMIN");
        Assert.assertEquals(loginPage.loginError.isPresentAndVisible(), true, "Expecting a login error");
        Assert.assertEquals(loginPage.loginError.getText(), "Authentication failed!");
    }

    @Test(dependsOnGroups = "loginPageUnauthenticated")
    public void testInvalidPasswordMixedCase() {
        login("admin", "Admin");
        Assert.assertEquals(loginPage.loginError.isPresentAndVisible(), true, "Expecting a login error");
        Assert.assertEquals(loginPage.loginError.getText(), "Authentication failed!");
    }

    @Test(dependsOnGroups = "loginPageUnauthenticated")
    public void testInvalidPasswordHiddenChar() {
        login("admin", "admin ");
        Assert.assertEquals(loginPage.loginError.isPresentAndVisible(), true, "Expecting a login error");
        Assert.assertEquals(loginPage.loginError.getText(), "Authentication failed!");
    }


    @Test(groups = "multipleLoginFailures", dependsOnGroups = "loginPageUnauthenticated")
    public void testMultipleFailedLoginAttempts() {
        // TODO - I would expect there to be some kind of lockout after several failed login attempts
        for (int i = 0; i < 20; i++) {
            login("admin", "admin ");
            Assert.assertEquals(loginPage.loginError.isPresentAndVisible(), true, "Expecting a login error");
            Assert.assertEquals(loginPage.loginError.getText(), "Authentication failed!");
        }
    }

    @Test(dependsOnGroups = "multipleLoginFailures")
    public void testLoginValidDoNotRemember() throws URISyntaxException {
        if (loginPage.automaticLoginCheckBox.isSelected())
            loginPage.automaticLoginCheckBox.click();
        loginPage.login("admin", "admin");
        homePageAuthenticated.waitForPageLoad().validate();

        // Logout and go back to login page
        homePageAuthenticated.logout();
        loginPage.open();
        loginPage.waitForPageLoad();
    }

    @Test(dependsOnGroups = "multipleLoginFailures")
    public void testLoginValid() throws URISyntaxException {
        if (!loginPage.automaticLoginCheckBox.isSelected())
            loginPage.automaticLoginCheckBox.click();
        loginPage.login("admin", "admin");
        homePageAuthenticated.waitForPageLoad().validate();

        // Logout and go back to login page
        homePageAuthenticated.logout();
        loginPage.open();
        loginPage.waitForPageLoad();
    }
}
