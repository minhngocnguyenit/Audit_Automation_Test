package com.sm.page;

import com.sm.assertion.CAssertion;
import com.sm.models.Control;
import com.sm.models.Romm;
import com.sm.page.components.CAttachCommonTool;
import com.sm.page.gct.GCTAttachment;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControlTestingPage extends BaseWorkingPaperPage {

    @FindBy(css =".control-details-table")
    private WebElement eleTableControlDetails;

    @FindBy(css = ".risks-table")
    private WebElement eleTableRisk;

    @FindBy(css = ".control-information-table")
    private WebElement eleControlInformation;

    @FindBy(css = ".control-conclustion-table")
    private WebElement eleControlConclusion;

    @FindBy(css = ".widget-attach-common-tool")
    private WebElement eleAttachCommonTool;

    private GCTAttachment gctAttachment;

    private static  ControlTestingPage controlTestingPage;

    public static ControlTestingPage getInstance(WebDriver webDriver) {
        if(controlTestingPage == null) return new ControlTestingPage(webDriver);
        return controlTestingPage;
    }

    public ControlTestingPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isLoad(){
        try {
            waitForVisibleElement(this.eleTableControlDetails, 60);
            return true;
        }catch (TimeoutException e) {

        }
        return false;
    }

    /**
     *
     * @param control
     */
    public void verifyControlDetailData(Control control) {
        List<WebElement> eleColumnControlDetails = this.eleTableControlDetails.findElements(By.cssSelector("tbody tr:nth-child(1) td span"));
        Assert.assertEquals("Control ID doesn't match", control.getControlId(), eleColumnControlDetails.get(0).getText());
        Assert.assertEquals("Control title doesn't match", control.getTitle(), eleColumnControlDetails.get(1).getText());
        Assert.assertEquals("Control description doesn't match", control.getDescription(), eleColumnControlDetails.get(2).getText());
    }

    /**
     *
     * @param control
     */
    public void verifyControlInformation(Control control) {
        //Verify control information
        List<WebElement> eleColumnControlInformation = this.eleControlInformation.findElements(By.cssSelector("tbody tr:nth-child(1) td span"));
        Assert.assertEquals("Control Nature doesn't match", control.getNature(), eleColumnControlInformation.get(0).getText());
        Assert.assertEquals("Control Frequency doesn't match", control.getFrequency(), eleColumnControlInformation.get(1).getText());
        Assert.assertEquals("Control Approach doesn't match", control.getApproach(), eleColumnControlInformation.get(2).getText());
        Assert.assertEquals("Control Type doesn't match", control.getType(), eleColumnControlInformation.get(3).getText());
        Assert.assertEquals("Control Testing Strategy doesn't match", control.getOeTestingStrategy(), eleColumnControlInformation.get(4).getText());
        Assert.assertEquals("Control Date Last Tested doesn't match", control.getOeDateLastTested(), eleColumnControlInformation.get(5).getText());
    }

    /**
     * Verify control conclusion
     * @param control
     */
    public void verifyControlConclusion(Control control) {
        //Verify control conclusion
        List<WebElement> eleColumnControlConclusion = this.eleControlConclusion.findElements(By.cssSelector("tbody tr:nth-child(1) td span"));
        Assert.assertEquals("Control Design Conclusion doesn't match", control.getDesignConclusion(), eleColumnControlConclusion.get(0).getText());
        Assert.assertEquals("Control Implementation Conclusion doesn't match", control.getImplementationConclusion(), eleColumnControlConclusion.get(1).getText());
        Assert.assertEquals("Control OE Conclusion doesn't match", control.getOeConclusion(), eleColumnControlConclusion.get(2).getText());
    }

    /**
     * Verify control data
     * @param romms
     */
    public void verifyRisk(List<Romm> romms) {
        //Verify on risk table
        List<WebElement> eleRowRisks = this.eleTableRisk.findElements(By.cssSelector("tbody tr"));

        for (Romm r: romms) {
            WebElement eleRow = eleRowRisks.stream().filter(e->{
                WebElement eleId = e.findElement(By.cssSelector("td:nth-child(2) span"));
                if(eleId.getText().contains(r.getId())){
                    return true;
                }
                return false;
            }).findFirst().orElse(null);
            CAssertion.assertNotNull("The Risk ID '" + r.getId() + "' doesn't exist.", eleRow);

            if(eleRow != null) {
                //Verify title
                WebElement eleTitle = eleRow.findElement(By.cssSelector("td:nth-child(3) span"));
                CAssertion.assertEquals(r.getTitle(), eleTitle.getText());

                //Verify assertion
                List<String> expectedAssertions = r.getAssertion().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                WebElement eleAssertion = eleRow.findElement(By.cssSelector("td:nth-child(4) span"));
                List<String> actualAssertions = Arrays.asList(eleAssertion.getText().split("\n")).stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                CAssertion.assertEquals(expectedAssertions, actualAssertions);
            }
        }
    }

    /**
     * Open attach common tool from attach common widget
     */
    public void openFileOnAttachCommonTool() {
        if(this.eleAttachCommonTool.isDisplayed()) {
            CAttachCommonTool cAttachCommonTool = new CAttachCommonTool(eleAttachCommonTool);
            cAttachCommonTool.showGCTAttachments();
        }
    }

    /**
     * Download file from attach common tool
     */
    public void downloadFileOnAttachCommonTool() {
        gctAttachment = GCTAttachment.getInstance(wedDriver);
        if(gctAttachment.isShowing()) {
            gctAttachment.downloadAttachFile();
            gctAttachment.closeGCT();
        }
    }
}
