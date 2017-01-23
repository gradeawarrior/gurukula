package com.gurukula.testlib.widgets;

import com.github.seleniumpm.webelements.Link;
import com.github.seleniumpm.webelements.Widget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntitiesWidget extends Widget {
    public Link branchLink;
    public Link staffLink;

    public EntitiesWidget(WebDriver driver, By locator) {
        super(driver, locator);
        branchLink = new Link(driver, By.xpath("//a/span[@translate='global.menu.entities.branch']"));
        staffLink = new Link(driver, By.xpath("//a/span[@translate='global.menu.entities.staff']"));
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
