package com.gurukula.testlib.widgets;

import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.pagemodel.webelements.Link;
import com.github.seleniumpm.pagemodel.webelements.Widget;
import org.openqa.selenium.By;

public class EntitiesWidget extends Widget {
    public Link branchLink;
    public Link staffLink;

    public EntitiesWidget(Selenium sel, Object locator) {
        super(sel, locator);
        branchLink = new Link(sel, By.xpath("//a/span[@translate='global.menu.entities.branch']"));
        staffLink = new Link(sel, By.xpath("//a/span[@translate='global.menu.entities.staff']"));
    }

    public EntitiesWidget waitForPresentAndVisible() {
        super.waitForPresentAndVisible();
        branchLink.waitForPresentAndVisible();
        staffLink.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        super.presentAndVisible();
        branchLink.presentAndVisible();
        staffLink.presentAndVisible();
    }
}
