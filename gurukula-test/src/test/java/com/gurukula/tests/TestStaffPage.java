package com.gurukula.tests;

import com.gurukula.GurukulaTest;
import com.gurukula.testlib.BranchPage;
import com.gurukula.testlib.HomePageAuthenticated;
import com.gurukula.testlib.LoginPage;
import com.gurukula.testlib.StaffsPage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TestStaffPage extends GurukulaTest {
    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;
    static BranchPage branchPage = null;
    static StaffsPage staffsPage = null;
    static List<String> branches = new ArrayList<String>(5);

    @BeforeClass
    public void classSetUp() throws Exception {
        try {
            // Instantiate all pages that will be visited
            homePageAuthenticated = new HomePageAuthenticated(browser, gurukulaURL);
            loginPage = new LoginPage(browser, gurukulaURL);
            branchPage = new BranchPage(browser, gurukulaURL);
            staffsPage = new StaffsPage(browser, gurukulaURL);

            // Login
            loginPage.open();
            loginPage.login("admin", "admin");
            homePageAuthenticated.waitForPageLoad().validate();
        } catch (Exception e) {
            if (browser != null)
                browser.quit();
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
        int pageCount = (initialCount < 20) ? initialCount : 19;

        // Create
        staffsPage.createStaffButton.click();
        staffsPage.createEditStaffWidget.waitForPresentAndVisible();
        staffsPage.createEditStaffWidget.nameField.clear().type(RandomStringUtils.random(20, true, false));
        staffsPage.createEditStaffWidget.saveButton.click();
        staffsPage.waitForPageLoad().validate();
        Assert.assertEquals(staffsPage.staffsTable.countRows(), pageCount + 1);
    }

    @Test(groups = "20Staff", dependsOnGroups = "createOneStaff")
    public void testCreateTwentyStaff() throws URISyntaxException {
        // Load Page
        staffsPage.open();
        staffsPage.waitForPageLoad().validate();
        int count = staffsPage.staffsTable.countRows();
        String branchName = null;
        int pageCount = 0;

        // Create
        for (int i = 0; i < 20; i++) {
            pageCount = (count < 20) ? count : 19;
            branchName = RandomStringUtils.random(20, true, false);
            staffsPage.createStaffButton.click();
            staffsPage.createEditStaffWidget.waitForPresentAndVisible();
            staffsPage.createEditStaffWidget.nameField.clear().type(branchName);
            staffsPage.createEditStaffWidget.saveButton.click();
            staffsPage.waitForPageLoad().validate();
            Assert.assertEquals(staffsPage.staffsTable.countRows(), pageCount + 1);
            branches.add(branchName);
        }
    }

    @Test(dependsOnGroups = "20Staff")
    public void testPagingForEveryTwentyStaff() throws URISyntaxException {
        // Load Page
        staffsPage.open();
        staffsPage.waitForPageLoad().validate();
        Assert.assertEquals(staffsPage.staffsTable.countRows(), 20, "Expect that there are no more than 20  branches on a page!");
    }
}
