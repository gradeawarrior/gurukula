package com.gurukula;

import com.google.common.base.Preconditions;
import com.gurukula.testlib.*;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.SeleniumWebdriver;
import org.apache.commons.lang.RandomStringUtils;
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
import java.util.ArrayList;
import java.util.List;

public class TestBranchPage {
    static Selenium sel = null;
    static String gurukulaURL = "http://localhost:8080";

    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;
    static BranchPage branchPage = null;
    static List<String> branches = new ArrayList<String>(5);

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
            branchPage = new BranchPage(sel);

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

    @Test(groups = "openBranch")
    public void testOpenBranchPage() throws URISyntaxException {
        branchPage.open();
        branchPage.waitForPageLoad().validate();
        Assert.assertEquals(branchPage.branchesTable.countRows() >= 0, true, "Expect there to be a table with 0 or more rows");
    }

    @Test(dependsOnGroups = "openBranch")
    public void testCreateABranch() throws URISyntaxException {
        // Load Page
        branchPage.open();
        branchPage.waitForPageLoad().validate();
        int initialCount = branchPage.branchesTable.countRows();

        // Create
        branchPage.createBranchButton.click();
        branchPage.createEditBranchWidget.waitForPresentAndVisible();
        branchPage.createEditBranchWidget.nameField.clear().type(RandomStringUtils.random(20, true, false));
        branchPage.createEditBranchWidget.codeField.clear().type(RandomStringUtils.random(10, true, true).toUpperCase());
        branchPage.createEditBranchWidget.saveButton.click();
        branchPage.waitForPageLoad().validate();
        Assert.assertEquals(branchPage.branchesTable.countRows(), initialCount + 1);
    }

    @Test(groups = "sixBranches", dependsOnGroups = "openBranch")
    public void testCreateSixBranches() throws URISyntaxException {
        // Load Page
        branchPage.open();
        branchPage.waitForPageLoad().validate();
        int count = branchPage.branchesTable.countRows();
        String branchName = null;

        // Create
        for (int i = 0; i < 6; i++) {
            branchName = RandomStringUtils.random(20, true, false);
            branchPage.createBranchButton.click();
            branchPage.createEditBranchWidget.waitForPresentAndVisible();
            branchPage.createEditBranchWidget.nameField.clear().type(branchName);
            branchPage.createEditBranchWidget.codeField.clear().type(RandomStringUtils.random(10, true, true).toUpperCase());
            branchPage.createEditBranchWidget.saveButton.click();
            branchPage.waitForPageLoad().validate();
            Assert.assertEquals(branchPage.branchesTable.countRows(), ++count);
            branches.add(branchName);
        }
    }

    @Test(dependsOnGroups = "sixBranches")
    public void testPagingForEveryFiveBranches() throws URISyntaxException {
        // Load Page
        branchPage.open();
        branchPage.waitForPageLoad().validate();
        Assert.assertEquals(branchPage.branchesTable.countRows(), 5, "Expect that there are no more than 5  branches on a page!");
    }

    @AfterClass
    public void classTearDown() {
        if (sel != null)
            sel.quit();
    }
}
