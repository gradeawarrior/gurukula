package com.gurukula.tests;

import com.gurukula.GurukulaTest;
import com.gurukula.testlib.*;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TestBranchPage extends GurukulaTest {
    static HomePageAuthenticated homePageAuthenticated = null;
    static LoginPage loginPage = null;
    static BranchPage branchPage = null;
    static List<String> branches = new ArrayList<String>(5);

    @BeforeClass
    public void classSetUp() throws Exception {
        try {
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

    @Test(groups = "createOneBranch", dependsOnGroups = "openBranch")
    public void testCreateBranch() throws URISyntaxException {
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

    @Test(groups = "sixBranches", dependsOnGroups = "createOneBranch")
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
}
