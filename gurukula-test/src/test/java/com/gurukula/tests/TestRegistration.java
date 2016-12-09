package com.gurukula.tests;

import com.gurukula.GurukulaTest;
import com.gurukula.testlib.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class TestRegistration extends GurukulaTest {
    static HomePageUnauthenticated homePageUnauthenticated = null;
    static HomePageAuthenticated homePageAuthenticated = null;
    static RegistrationPage registrationPage = null;

    @BeforeClass
    public void classSetUp() throws Exception {
        try {
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
}
