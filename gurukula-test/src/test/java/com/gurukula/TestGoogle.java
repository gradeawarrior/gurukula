package com.gurukula;

import com.gurukula.testlib.GooglePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This is a sample Google test using a combination of the Selenium WebDriver example and the included PageObject
 * implementation. For the original Google Webdriver example, see: http://www.seleniumhq.org/docs/03_webdriver.jsp
 */
public class TestGoogle {

    @Test
    public void testSearchGoogle() throws MalformedURLException, InterruptedException {
        // TODO: This should be set via PATH=/Users/psalas/Downloads/geckodriver and the selenium-server started remotely
        // System.setProperty("webdriver.gecko.driver", "/Users/psalas/Downloads/geckodriver");
        WebDriver browser = null;

        try {
            // Specifying where the tests will run will be based on URL
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("firefox");
            browser = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            GooglePage google = new GooglePage(browser);
            String searchTerm = "Cheese!";

            // And now use this to visit Google
            google.open();

            // Enter something to search for
            google.searchField.type(searchTerm);

            // Now submit the form. WebDriver will find the form for us from the element
            google.searchField.submit();

            // Check the title of the page
            String title = google.getTitle();
            System.out.println("Page title is: " + title);
            // Should see: "cheese! - Google Search"
            title = google.waitForTitle(searchTerm).getTitle();
            System.out.println("Page title is: " + title);
            Assert.assertEquals(title, searchTerm + " - Google Search", "Expecting the title to be the same as the search term");
        } finally {
            //Close the browser
            if (browser != null)
                browser.quit();
        }
    }
}
