package com.sm.page;

import com.sm.page.PageInit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EngagementSettingPage extends PageInit {

    private static EngagementSettingPage engagementSettingPage;

    @FindBy(css = "input[name='reportDate']")
    private WebElement eleReportDate;

    @FindBy(css = "input[name='archiveControlDate']")
    private WebElement eleArchiveDate;

    @FindBy(css = ".engagmenet-settings-page .primary")
    private WebElement eleSaveChanges;

    @FindBy(css = ".engagmenet-settings-page .primary.loading")
    private WebElement eleLoadingButton;

    public EngagementSettingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static EngagementSettingPage getInstance(WebDriver webDriver) {
        if(engagementSettingPage == null) return new EngagementSettingPage(webDriver);
        return engagementSettingPage;
    }

    /**
     * Set report date
     * @param date
     */
    public void setReportDate(String date) {
        click(eleReportDate);
        waitSomeSecond(0.5);
        eleReportDate.sendKeys(Keys.ENTER);
        waitForElementToBeClickable(eleSaveChanges, 5);
        click(eleSaveChanges);
        waitForVisibleElement(eleLoadingButton, 5);
        waitUtilElementHidden(eleLoadingButton, 60);

    }

    /**
     * Set archive date
     * @param date
     */
    public void setArchiveDate(String date) {
        click(eleArchiveDate);
        waitSomeSecond(0.5);
        eleArchiveDate.sendKeys(Keys.ENTER);
        waitForElementToBeClickable(eleSaveChanges, 5);
        click(eleSaveChanges);
        waitForVisibleElement(eleLoadingButton, 5);
        waitUtilElementHidden(eleLoadingButton, 60);
    }
}
