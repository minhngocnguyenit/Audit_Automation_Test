package com.sm.page.gct;

import com.sm.page.PageInit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneralCommonTool extends PageInit {

    public static final String REVIEW_NOTES = "REVIEW NOTES";
    public static final String FLAGS = "FLAGS";
    public static final String TICKMARKS = "TICKMARKS";
    public static final String CROSS_REFERENCES = "CROSS REFERENCES";
    public static final String ATTACHMENTS = "ATTACHMENTS";
    public static final String RISK = "RISKS";
    public static final String CONTROL = "CONTROL";

    @FindBy(css = ".review-note-modal")
    protected WebElement gctModal;

    @FindBy(css = ".review-note-modal .view-content.open")
    protected WebElement gctMdodalContent;

    @FindBy(css = ".review-note-modal .view-content .type")
    protected WebElement eleTitle;

    @FindBy(css = ".review-note-modal .view-content .close-btn")
    protected WebElement eleCloseBtn;

    @FindBy(css = ".review-note-modal  .form-buttons button:nth-child(1)")
    protected WebElement eleCancelBtn;

    @FindBy(css = ".review-note-modal  .form-buttons button:nth-child(2)")
    protected WebElement eleCreateBtn;

    public boolean isReviewNote(){
        return eleTitle.getText().equals(REVIEW_NOTES);
    }

    public boolean isFlags() {
        return  eleTitle.getText().equals(FLAGS);
    }

    public boolean isTickmark() {
        return eleTitle.getText().equals(TICKMARKS);
    }

    public boolean isCrossReference() {
        return eleTitle.getText().equals(CROSS_REFERENCES);
    }

    public boolean isAttachment() {
        return  eleTitle.getText().equals(ATTACHMENTS);
    }

    public boolean isRisk() {
        return eleTitle.getText().equals(RISK);
    }

    public boolean isControl() { return  eleTitle.getText().equals(CONTROL); }

    public GeneralCommonTool(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isShowing() {
        try{
            WebElement content = gctModal.findElement(By.cssSelector(".view-content.open"));
            return content != null && content.isDisplayed();
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public void closeGCT() {
        click(eleCloseBtn);
    }

    public void cancel() {
        click(eleCancelBtn);
    }

    public void createOrSave() {
        waitForElementToBeClickable(eleCreateBtn, 20);
        if(eleCreateBtn.isEnabled()) {
            click(eleCreateBtn);
            waitUtilGCTClosed();
            waitForDimmerDisappeared();
        }
    }

    public void waitUtilGCTClosed() {
        waitUtilElementHidden(gctMdodalContent, 30);
    }
}
