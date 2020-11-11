package com.sm.page;

import com.sm.helper.AccountingFormatter;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConcludingAnalyticalProceduresPage extends BaseWorkingPaperPage{

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static ConcludingAnalyticalProceduresPage concludingAnalyticalProceduresPage;

    public ConcludingAnalyticalProceduresPage(WebDriver driver) {
        super(driver);
    }
    public static ConcludingAnalyticalProceduresPage getInstance(WebDriver webDriver) {
        if (concludingAnalyticalProceduresPage == null) return new ConcludingAnalyticalProceduresPage(webDriver);
        return concludingAnalyticalProceduresPage;
    }

    @FindBy(xpath="//div[@class = 'active item']")
    WebElement eleCarPlanning;

    @FindBy(css=".risk-common-tool-wrapper > td:nth-child(3)")
    WebElement eleOverall;

    @FindBy(css=".risk-common-tool-wrapper > td:nth-child(4)")
    WebElement elePerformanceMateriality;

    @FindBy(css=".risk-common-tool-wrapper > td:nth-child(5)")
    WebElement eleCTT;

    @FindBy(xpath="(//input)[1]")
    WebElement eleThresholdAmount;

    @FindBy(xpath="(//input)[2]")
    WebElement eleThresholdPercentage;

    @FindBy(xpath = "(//div[@class='jet-border-bottom-header-cell']/i[contains(@class, 'jet-parGrid-checkbox')])[1]")
    WebElement eleCheckAllAccountBalance;

    @FindBy(xpath="(//div[@class='filter-box']/div[contains(@class,  'car-action parent-dropdown')])[1]")
    WebElement eleActionMenuAccountBalance;

    @FindBy(xpath="(//div[contains(@class, 'car-action')])[1]/div[contains(@class, 'menu')]/div[2]")
    WebElement eleQualitativelyMaterialAccountBalance;

    @FindBy(xpath="((//div[contains(@class, 'car-action')])[1]/div[contains(@class, 'menu')]//div[contains(@class, 'menu')])[2]/div[1]")
    WebElement eleQualitativelyMaterialYesAccountBalance;

    @FindBy(xpath="(//div[@class='jet-border-bottom-header-cell']/i[contains(@class, 'jet-parGrid-checkbox')])[2]")
    WebElement eleCheckAllClassOfTransactions;

    @FindBy(xpath="(//div[@class='filter-box']/div[contains(@class,  'car-action parent-dropdown')])[2]")
    WebElement eleActionMenuClassOfTransactions;

    @FindBy(xpath="(//div[contains(@class, 'car-action')])[2]/div[contains(@class, 'menu')]/div[2]")
    WebElement eleQualitativelyMaterialClassOfTransactions;

    @FindBy(xpath="((//div[contains(@class, 'car-action')])[2]/div[contains(@class, 'menu')]//div[contains(@class, 'menu')])[2]/div[1]")
    WebElement eleQualitativelyMaterialYesClassOfTransactions;

    @Override
    public boolean isLoad(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementToBeClickable(this.eleCarPlanning);
        return this.eleCarPlanning.isDisplayed();
    }

    //Set up Par&Scoping
    public void SetUpCar(double amount, double percent) {
        //input Threshold
        logger.info("-- I type threshold amount: " + amount);
        waitForElementToBeClickable(this.eleThresholdAmount);
        this.eleThresholdAmount.clear();
        this.eleThresholdAmount.sendKeys(String.valueOf(amount));

        logger.info("-- I type threshold percent: " + percent);
        waitForElementToBeClickable(this.eleThresholdPercentage);
        this.eleThresholdPercentage.clear();
        this.eleThresholdPercentage.sendKeys(String.valueOf(percent));

        //Set Movement explanation of Account Balance table to Yes
        logger.info("-- I click Checkbox all on Account Balance table");
        waitForElementToBeClickable(eleCheckAllAccountBalance, 5);
        click(this.eleCheckAllAccountBalance);

        logger.info("-- I click action menu on Account Balance table");
        waitForElementToBeClickable(eleActionMenuAccountBalance, 5);
        click(this.eleActionMenuAccountBalance);

        logger.info("-- I click Qualitatively Material on Account Balance table");
        waitForElementToBeClickable(this.eleQualitativelyMaterialAccountBalance, 5);
        click(this.eleQualitativelyMaterialAccountBalance);

        logger.info("-- I select Yes for option Qualitatively Material on Account Balance table");
        waitForElementToBeClickable(this.eleQualitativelyMaterialYesAccountBalance, 5);
        this.eleQualitativelyMaterialYesAccountBalance.click();

        //Set Movement explanation of Class of Transactions table to Yes
        logger.info("-- I click on Checkbox all on Class of Transaction table");
        waitForElementToBeClickable(eleCheckAllClassOfTransactions, 5);
        click(this.eleCheckAllClassOfTransactions);

        logger.info("-- I click on action Menu on Class of Transaction table");
        waitForElementToBeClickable(eleActionMenuClassOfTransactions, 5);
        click(this.eleActionMenuClassOfTransactions);

        logger.info("-- I select Qualitatively Material on Class of Transaction table");
        waitForElementToBeClickable(this.eleQualitativelyMaterialClassOfTransactions);
        click(this.eleQualitativelyMaterialClassOfTransactions);

        logger.info("-- I click on Yes for Qualitatively Material on Class of Transaction table");
        waitForElementToBeClickable(this.eleQualitativelyMaterialYesClassOfTransactions);
        click(this.eleQualitativelyMaterialYesClassOfTransactions);
    }

    public void checkOverall(double overall, double pm, double ctt){
        //Wait for Engagement materiality is displayed on PAR&Scoping
        waitForElementToBeClickable(this.eleCarPlanning);
        String actualOverall = this.eleOverall.getText();
        String actualPM = this.elePerformanceMateriality.getText();
        String actualCTT = this.eleCTT.getText();

        Assert.assertEquals(AccountingFormatter.format(overall, locale), actualOverall);
        Assert.assertEquals(AccountingFormatter.format(pm, locale), actualPM);
        Assert.assertEquals(AccountingFormatter.format(ctt, locale), actualCTT);
    }
}
