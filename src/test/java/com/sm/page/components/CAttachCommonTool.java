package com.sm.page.components;

import com.sm.page.PageInit;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CAttachCommonTool extends PageInit {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private WebElement ele;

    public CAttachCommonTool(WebElement ele) {
        this.ele = ele;
    }

    public WebElement findReviewNoteGCTIcon() {
        return ele.findElement(By.cssSelector(".sum-icon .icon-item.reviewnote i"));
    }

    public WebElement findFlagGCTIcon() {
        return ele.findElement(By.cssSelector(".sum-icon .icon-item.riskflagopen i"));
    }

    public WebElement findTickmarkGCTIcon() {
        return ele.findElement(By.cssSelector(".sum-icon .icon-item.tickmark i"));
    }

    public WebElement findCrossReferenceGCTIcon() {
        return ele.findElement(By.cssSelector(".sum-icon .icon-item.crossreference i"));
    }

    public WebElement findAttachmentGCTIcon() {
        return ele.findElement(By.cssSelector(".sum-icon .icon-item.attachment i"));
    }

    public int getNumberFile() {
        WebElement eleNumberOfFile = ele.findElement(By.cssSelector(".sum-icon .icon-item.attachment"));
        if(eleNumberOfFile == null || !eleNumberOfFile.isDisplayed()) return 0;
        try{
            return Integer.parseInt(eleNumberOfFile.getText());
        }catch (NumberFormatException ignored) {
        }
        return 0;
    }

    public void showGCTAttachments() {
        try {
            waitSomeSecond(3);
            int i = getNumberFile();
            if(i > 0) {
                WebElement icon = findAttachmentGCTIcon();
                if(icon == null) return;
                if(icon.isEnabled()) {
                    click(icon);
                }
            }
        }catch (Exception e) {
            logger.error(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
