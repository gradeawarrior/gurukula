package com.gurukula;

import com.google.common.base.Preconditions;
import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.SeleniumWebdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URI;
import java.net.URL;

public class GurukulaTest {
    protected static Selenium sel = null;
    protected static String gurukulaURL = "http://localhost:8080";

    @BeforeSuite
    public void suiteSetUp() throws Exception {
        String server = System.getProperty("selenium.server", "http://localhost:4444") + "/wd/hub";
        gurukulaURL = System.getProperty("gurukula.url", "http://localhost:8080");
        String browserType = System.getProperty("browser.type", "chrome");

        Preconditions.checkNotNull(gurukulaURL, "Gurukula URL is not null!");
        Preconditions.checkArgument(gurukulaURL.length() > 0, "Gurukula URL is not empty!");
        try {
            // Specifying where the tests will run will be based on URL
            WebDriver browser;
            if (browserType.equals("firefox")) {
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                browser = new RemoteWebDriver(new URL(server), capabilities);
            } else if (browserType.equals("chrome")) {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                browser = new RemoteWebDriver(new URL(server), capabilities);
            } else if (browserType.equals("safari")) {
                DesiredCapabilities capabilities = DesiredCapabilities.safari();
                browser = new RemoteWebDriver(new URL(server), capabilities);
            } else if (browserType.equals("headless")) {
                browser = new HtmlUnitDriver();
            } else {
                throw new UnsupportedOperationException("Cannot launch browserType: '" + browserType + "'!");
            }

            // Instantiate Selenium
            sel = new SeleniumWebdriver(browser, new URI(gurukulaURL));
        } catch (Exception e) {
            if (sel != null)
                sel.quit();
            throw e;
        }
    }

    @AfterSuite
    public void suiteTearDown() {
        if (sel != null)
            sel.quit();
    }
}
