package com.gurukula.testlib.widgets;

import com.github.seleniumpm.webelements.Link;
import com.github.seleniumpm.webelements.Widget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderWidget extends Widget {
    public Link gurukulaBannerLink;
    public Link homeLink;
    public EntitiesWidget entitiesWidget;
    public AccountsWidget accountsWidget;

    public HeaderWidget(WebDriver driver, By locator) {
        super(driver, locator);
        gurukulaBannerLink = new Link(driver, By.xpath("//div/a[contains(@class, 'navbar-brand')]"));
        homeLink = new Link(driver, By.xpath("//a/span[@translate='global.menu.home']"));
        entitiesWidget = new EntitiesWidget(driver, By.xpath("//a/span/span[@translate='global.menu.entities.main']"));
        accountsWidget = new AccountsWidget(driver, By.xpath("//a/span/span[@translate='global.menu.account.main']"));
    }

    public HeaderWidget waitForPresentAndVisible() {
        super.waitForPresentAndVisible();
        gurukulaBannerLink.waitForPresentAndVisible();
        homeLink.waitForPresentAndVisible();
        accountsWidget.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        super.presentAndVisible();
        gurukulaBannerLink.waitForPresentAndVisible();
        homeLink.waitForPresentAndVisible();
        accountsWidget.waitForPresentAndVisible();
    }
}
