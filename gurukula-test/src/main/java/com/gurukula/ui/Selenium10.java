package com.gurukula.ui;

import com.thoughtworks.selenium.DefaultSelenium;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Selenium10 extends DefaultSelenium implements Selenium {

    protected long elementWaitTime = 10000;
    protected long pageWaitTime = 30000;
    protected long sleepTimeInMilis = 1000;

    public Selenium10(String serverHost, int serverPort, String browserStartCommand, String browserURL) {
        super(serverHost, serverPort, browserStartCommand, browserURL);
    }

    public WebDriver getDriver() {
        throw new NotImplementedException("getDriver is only available in Selenium WebDriver!");
    }

    public void quit() {
        super.close();
    }

    public Selenium click(Object locator) {
        super.click((String) locator);
        return this;
    }

    public Selenium clickAndWait(Object locator) {
        return click(locator).waitForPageToLoad();
    }

    public Selenium waitForPresent(Object locator) {
        throw new NotImplementedException();
    }

    public Selenium waitForVisible(Object locator) {
        throw new NotImplementedException();
    }

    public Selenium waitForPresentAndVisible(Object locator) {
        return waitForPresent(locator).waitForVisible(locator);
    }

    public Selenium waitForPresent(Object locator, long waitTime) throws NotFoundException {
        long currentTime = System.nanoTime();
        long endTime = currentTime + TimeUnit.MILLISECONDS.toNanos(elementWaitTime);

        while (currentTime <= endTime) {
            if (isPresent(locator))
                return this;
            try {
                Thread.sleep(sleepTimeInMilis);
            } catch (InterruptedException ie) {
                throw new NotFoundException("An InterruptedException occured!");
            }
            currentTime = System.nanoTime();
        }
        throw new NotFoundException(locator + "was not found in " + elementWaitTime + "ms!");
    }

    public Selenium waitForVisible(Object locator, long waitTime) throws NotFoundException {
        long currentTime = System.nanoTime();
        long endTime = currentTime + TimeUnit.MILLISECONDS.toNanos(elementWaitTime);

        while (currentTime <= endTime) {
            if (isVisible(locator))
                return this;
            try {
                Thread.sleep(sleepTimeInMilis);
            } catch (InterruptedException ie) {
                throw new NotFoundException("An InterruptedException occured!");
            }
            currentTime = System.nanoTime();
        }
        throw new NotFoundException(locator + "was not visible in " + elementWaitTime + "ms!");
    }

    public Selenium waitForPresentAndVisible(Object locator, long waitTime) {
        return null;
    }

    public Selenium waitForPageToLoad(long timeout) {
        super.waitForPageToLoad(Long.toString(timeout));
        return this;
    }

    public Selenium waitForPageToLoad() {
        super.waitForPageToLoad(Long.toString(pageWaitTime));
        return this;
    }

    public Selenium type(Object locator, String txt) {
        super.type((String) locator, txt);
        return this;
    }

    public boolean isPresent(Object locator) {
        return super.isElementPresent((String) locator);
    }

    public boolean isVisible(Object locator) {
        return super.isVisible((String) locator);
    }

    public boolean isDisplayed(Object locator) {
        throw new NotImplementedException("isDisplayed is not available in Selenium 1.0!");
    }

    public boolean isEnabled(Object locator) {
        throw new NotImplementedException("isEnabled is not available in Selenium 1.0!");
    }

    public boolean isSelected(Object locator) {
        throw new NotImplementedException("isSelected is not available in Selenium 1.0!");
    }

    public boolean isPresentAndVisible(Object locator) {
        return isPresent(locator) && isVisible(locator);
    }

}
