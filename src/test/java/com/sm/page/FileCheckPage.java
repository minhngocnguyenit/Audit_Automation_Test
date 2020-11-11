package com.sm.page;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FileCheckPage extends BaseWorkingPaperPage {

    private static FileCheckPage fileCheckPage;

    @FindBy(css = ".file-check-modal.mini-modal")
    private WebElement eleFileCheckModal;

    public FileCheckPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".file-check .group-name")
    private WebElement eleFileCheckTitle;

    @FindBy(css = ".file-check-modal .group-edit .link-button")
    private WebElement eleExportBtn;

    @FindBy(css = ".file-check .confirmBtn")
    private WebElement eleConfirmModal;

    public static FileCheckPage getInstance(WebDriver webDriver) {
        if(fileCheckPage == null) return new FileCheckPage(webDriver);
        return fileCheckPage;
    }

    @Override
    public boolean isLoad() {
        waitForDimmerDisappeared();
        String title = eleFileCheckTitle.getText();
        return title.equals("File Check");
    }

    public void checkFileCheckModal() {
        boolean rs = false;
        try{
            waitForVisibleElement(eleFileCheckModal, 5);
            rs = true;
        }catch (Exception e){

        }
        Assert.assertTrue("The file check modal doesn't display", rs);
    }

    public void closeFileCheckModal() {
        click(eleConfirmModal);
        waitUtilElementHidden(eleFileCheckModal, 5);
    }

    public void export() {
        click(eleExportBtn);
        waitSomeSecond(5);
    }
}
