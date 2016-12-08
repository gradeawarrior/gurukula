package com.gurukula;

import com.google.common.base.Preconditions;
import com.gurukula.testlib.BranchPage;
import com.gurukula.testlib.HomePageAuthenticated;
import com.gurukula.testlib.LoginPage;
import com.gurukula.testlib.StaffsPage;
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

public class TestStaffPage {
    static Selenium sel = null;
    static String gurukulaURL = "http://localhost:8080";

    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;
    static BranchPage branchPage = null;
    static StaffsPage staffsPage = null;
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
            staffsPage = new StaffsPage(sel);

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

    @Test(groups = "openStaff")
    public void testOpenStaffPage() throws URISyntaxException {
        staffsPage.open();
        staffsPage.waitForPageLoad().validate();
        Assert.assertEquals(staffsPage.staffsTable.countRows() >= 0, true, "Expect there to be a table with 0 or more rows");
    }

    @Test(groups = "createOneStaff", dependsOnGroups = "openStaff")
    public void testCreateStaff() throws URISyntaxException {
        // Load Page
        staffsPage.open();
        staffsPage.waitForPageLoad().validate();
        int initialCount = staffsPage.staffsTable.countRows();

        // Create
        staffsPage.createStaffButton.click();
        staffsPage.createEditStaffWidget.waitForPresentAndVisible();
        staffsPage.createEditStaffWidget.nameField.clear().type(RandomStringUtils.random(20, true, false));
        staffsPage.createEditStaffWidget.saveButton.click();
        staffsPage.waitForPageLoad().validate();
        Assert.assertEquals(staffsPage.staffsTable.countRows(), initialCount + 1);
    }

    @Test(groups = "sixStaff", dependsOnGroups = "createOneStaff")
    public void testCreateSixStaff() throws URISyntaxException {
        // Load Page
        staffsPage.open();
        staffsPage.waitForPageLoad().validate();
        int count = staffsPage.staffsTable.countRows();
        String branchName = null;

        // Create
        for (int i = 0; i < 6; i++) {
            branchName = RandomStringUtils.random(20, true, false);
            staffsPage.createStaffButton.click();
            staffsPage.createEditStaffWidget.waitForPresentAndVisible();
            staffsPage.createEditStaffWidget.nameField.clear().type(branchName);
            staffsPage.createEditStaffWidget.saveButton.click();
            staffsPage.waitForPageLoad().validate();
            Assert.assertEquals(staffsPage.staffsTable.countRows(), ++count);
            branches.add(branchName);
        }
    }

    @Test(dependsOnGroups = "sixStaff")
    public void testPagingForEveryFiveStaff() throws URISyntaxException {
        // Load Page
        staffsPage.open();
        staffsPage.waitForPageLoad().validate();
        Assert.assertEquals(staffsPage.staffsTable.countRows(), 5, "Expect that there are no more than 5  branches on a page!");
    }

    @AfterClass
    public void classTearDown() {
        if (sel != null)
            sel.quit();
    }
}
