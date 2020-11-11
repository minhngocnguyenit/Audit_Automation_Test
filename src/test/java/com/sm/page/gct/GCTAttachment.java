package com.sm.page.gct;

import com.sm.assertion.CAssertion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.io.File;
import java.util.List;

public class GCTAttachment extends GeneralCommonTool {

    private static GCTAttachment GCTAttachment;

    @FindBy(css = ".review-note-modal .attachment-item p")
    private List<WebElement> lstAttachFiles;

    @FindBy(css = ".review-note-modal .detail-document-file")
    private WebElement eleDetailAttachmentFile;

    @FindBy(css = ".review-note-modal .attachment-btn-group .button:nth-child(1)")
    private WebElement eleReplaceBtn;

    @FindBy(css = ".review-note-modal .attachment-btn-group .button:nth-child(2)")
    private WebElement eleDownloadBtn;

    @FindBy(css = ".auv-confirmationModal")
    private WebElement eleConfirmModel;

    @FindBy(css = ".auv-confirmationModal .confirmBtn")
    private WebElement eleConfirmDownload;

    public GCTAttachment(WebDriver webDriver) {
        super(webDriver);
    }

    public static GCTAttachment getInstance(WebDriver webDriver) {
        if(GCTAttachment == null) return new GCTAttachment(webDriver);
        return GCTAttachment;
    }

    public boolean isShowing() {
        return super.isShowing() && super.isAttachment();
    }

    public String getFileName() {
        return this.eleDetailAttachmentFile.getText();
    }

    public void downloadAttachFile() {
        try{
            WebElement e = lstAttachFiles.stream().findAny().orElse(null);
            if(e != null) click(e);
            waitForElementToBeClickable(eleDownloadBtn);
            click(eleDownloadBtn);
            Thread.sleep(2000);
            String downloadFilepath = System.getProperty("user.dir") + File.separator + "download";
            File downloadFolder = new File(downloadFilepath);
            File[] files = downloadFolder.listFiles();
            boolean rs = false;
            for(File f : files) {
                if(f.getName().contains(getFileName())) {
                    rs = true;
                    break;
                }
            }
            CAssertion.assertTrue(rs);
        }catch (Exception e) {

        }

        //waitForVisibleElement(this.eleConfirmModel, 120);
//        click(eleConfirmDownload);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
    }
}
