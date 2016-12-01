package com.gurukula.testlib.widgets;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.Link;
import com.gurukula.ui.pagemodel.webelements.Widget;
import org.openqa.selenium.By;

public class HeaderWidget extends Widget {
    public Link gurukulaBannerLink;
    public Link homeLink;
    public EntitiesWidget entitiesWidget;
    public AccountsWidget accountsWidget;

    public HeaderWidget(Selenium sel, Object locator) {
        super(sel, locator);
        gurukulaBannerLink = new Link(sel, By.xpath("//div/a[contains(@class, 'navbar-brand')]"));
        homeLink = new Link(sel, By.xpath("//a/span[@translate='global.menu.home']"));
        entitiesWidget = new EntitiesWidget(sel, By.xpath("//a/span/span[@translate='global.menu.entities.main']"));
        accountsWidget = new AccountsWidget(sel, By.xpath("//a/span/span[@translate='global.menu.account.main']"));
    }

    public HeaderWidget waitForPresentAndVisible() {
        super.waitForPresentAndVisible();
        gurukulaBannerLink.waitForPresentAndVisible();
        homeLink.waitForPresentAndVisible();
        accountsWidget.waitForPresentAndVisible();
        return this;
    }
}
