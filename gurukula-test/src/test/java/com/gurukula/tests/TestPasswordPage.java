package com.gurukula.tests;

import com.gurukula.GurukulaTest;
import com.gurukula.testlib.HomePageAuthenticated;
import com.gurukula.testlib.LoginPage;
import com.gurukula.testlib.PasswordPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

@Test
public class TestPasswordPage extends GurukulaTest {

    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;
    static PasswordPage passwordPage = null;

    @BeforeClass
    public void classSetUp() throws Exception {
        try {
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
}
