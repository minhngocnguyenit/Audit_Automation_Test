package com.sm.page;

import com.sm.assertion.CAssertion;
import com.sm.helper.AccountingFormatter;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.Assertion;

import java.util.Locale;

public class ParScopingPage extends BaseWorkingPaperPage{
    private static ParScopingPage parScopingPage;
    private Logger logger = Logger.getLogger(ParScopingPage.class.getName());

    public ParScopingPage(WebDriver driver) {
        super(driver);
    }
    public static ParScopingPage getInstance(WebDriver webDriver) {
        if (parScopingPage == null) return new ParScopingPage(webDriver);
        return parScopingPage;
         }

    @FindBy(xpath="//div[@class = 'active item']")
    WebElement eleParPlanning;

    @FindBy(css=".risk-common-tool-wrapper > td:nth-child(3)")
    WebElement eleOverall;

    @FindBy(css=".risk-common-tool-wrapper > td:nth-child(4)")
    WebElement elePerformanceMateriality;

    @FindBy(css=".risk-common-tool-wrapper > td:nth-child(5)")
    WebElement eleCTT;

    @FindBy(css=".risk-common-tool-wrapper:nth-child(2) > td:nth-child(3)")
    WebElement eleCustomABCOTD;

    @FindBy(css=".risk-common-tool-wrapper:nth-child(2) > td:nth-child(4)")
    WebElement eleCustomPerformanceMateriality;

    @FindBy(css=".risk-common-tool-wrapper:nth-child(2) > td:nth-child(5)")
    WebElement eleCustomCTT;

    @FindBy(xpath = "(//input)[1]")
    WebElement eleThresholdAmount;

    @FindBy(xpath = "(//input)[2]")
    WebElement eleThresholdPercentage;

    @FindBy(xpath = "(//div[@class='jet-border-bottom-header-cell']/i[contains(@class, 'jet-parGrid-checkbox')])[1]")
    WebElement eleCheckAllAccountBalance;

    @FindBy(xpath="(//div[@class='filter-box']//div[@class='ui button item dropdown basic par-action parent-dropdown'])[1]")
    WebElement eleActionMenuAccountBalance;

    @FindBy(xpath="(//div[contains(@class, 'par-action')])[1]/div[contains(@class, 'menu')]/div[2]")
    WebElement eleQualitativelyMaterial1;

    @FindBy(xpath="//div[contains(@class,'menu') and contains(@class,'visible')]/div/*[text()='Yes']")
    WebElement eleQualitativelyMaterialYes1;

    @FindBy(xpath="(//div[@class='jet-border-bottom-header-cell']/i[contains(@class, 'jet-parGrid-checkbox')])[2]")
    WebElement eleCheckAllClassOfTransactions;

    @FindBy(xpath="(//div[@class='filter-box']/div[contains(@class,  'par-action parent-dropdown')])[2]")
    WebElement eleActionMenuClassOfTransactions;

    @FindBy(xpath="(//div[contains(@class, 'par-action')])[2]/div[contains(@class, 'menu')]/div[2]")
    WebElement eleQualitativelyMaterial2;

    @FindBy(xpath="((//div[contains(@class, 'par-action')])[2]/div[contains(@class, 'menu')]//div[contains(@class, 'menu')])[2]/div[1]")
    WebElement eleQualitativelyMaterialYes2;

    @Override
    public boolean isLoad(){
        waitForElementToBeClickable(this.eleParPlanning);
        return this.eleParPlanning.isDisplayed();
    }

    //Set up Par&Scoping
    public void SetUpPar(double amount, double percent) throws InterruptedException {
        //input Threshold
        logger.info("-- I enter threshold amount: "+ amount);
        waitForElementToBeClickable(this.eleThresholdAmount, 5);
        this.eleThresholdAmount.clear();
        this.eleThresholdAmount.sendKeys(String.valueOf(amount));

        logger.info("-- I enter threshold percent: "+ percent);
        waitForElementToBeClickable(this.eleThresholdPercentage, 5);
        this.eleThresholdPercentage.clear();
        this.eleThresholdPercentage.sendKeys(String.valueOf(percent));
        waitSomeSecond(1);

        //Set Qualitatively material of Account Balance table to Yes
        logger.info("-- I click on checkbox on Account Balance table");
        click(this.eleCheckAllAccountBalance);
        logger.info("-- I click on Action menu on Account Balance table");
        click(this.eleActionMenuAccountBalance);
        waitForElementToBeClickable(this.eleQualitativelyMaterial1, 5);
        logger.info("-- I select Qualitatively Materiality option on Account Balance table");
        click(this.eleQualitativelyMaterial1);

        waitSomeSecond(1);
        waitForElementToBeClickable(this.eleQualitativelyMaterialYes1, 5);
        logger.info("-- I select Yes for Qualitatively Materiality on Account Balance table");
        this.eleQualitativelyMaterialYes1.click();
        waitSomeSecond(1);

        //Set Qualitatively material of Class of Transactions table to Yes
        logger.info("-- I click on check box All on Class Of Transactions table");
        click(this.eleCheckAllClassOfTransactions);
        logger.info("-- I select Action menu on Class Of Transaction table");
        click(this.eleActionMenuClassOfTransactions);
        waitForElementToBeClickable(this.eleQualitativelyMaterial2, 5);

        logger.info("-- I select Qualitatively Material option on Class Of Transaction table");
        click(this.eleQualitativelyMaterial2);
        waitSomeSecond(1);
        waitForElementToBeClickable(this.eleQualitativelyMaterialYes2, 5);
        logger.info("-- I select Yes for Qualitative Material on Class Of Transaction table");
        click(this.eleQualitativelyMaterialYes2);
        waitSomeSecond(1);
    }

    public void checkOverall(double overall, double pm, double ctt){
        //Wait for Engagement materiality is displayed on PAR&Scoping
        waitForElementToBeClickable(this.eleParPlanning);
        String actualOverall = this.eleOverall.getText();
        String actualPM = this.elePerformanceMateriality.getText();
        String actualCTT = this.eleCTT.getText();

        Assert.assertEquals(AccountingFormatter.format(overall, locale), actualOverall);
        Assert.assertEquals(AccountingFormatter.format(pm, locale), actualPM);
        Assert.assertEquals(AccountingFormatter.format(ctt, locale), actualCTT);
    }
}
