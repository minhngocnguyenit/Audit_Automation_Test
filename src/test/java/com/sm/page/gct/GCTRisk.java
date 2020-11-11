package com.sm.page.gct;

import com.sm.models.Romm;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GCTRisk extends GeneralCommonTool {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static GCTRisk gctRisk;

    @FindBy(css = ".create-risk-common")
    private WebElement ele;

    @FindBy(css = ".create-risk-common .cls-associated-width .field .checkbox label")
    private List<WebElement> eleRiskType;

    @FindBy(css = ".create-risk-common .associated-abcotd")
    private WebElement eleAssociatedAbcotds;

    @FindBy(css = ".create-risk-common input[name='riskTitle']")
    private WebElement eleRiskTitle;

    @FindBy(css = ".create-risk-common textarea[name='riskDescription']")
    private WebElement eleRiskDescription;

    @FindBy(css = ".create-risk-common .assertion")
    private WebElement eleAssociatedAssertions;

    @FindBy(css = ".create-risk-common .riskLevel div[name='riskLevel']")
    private WebElement eleRiskClassification;

    @FindBy(css = ".create-risk-common textarea[name='riskLevelRationale']")
    private WebElement eleRiskClassificationRationale;

    public GCTRisk(WebDriver webDriver) {
        super(webDriver);
    }

    public static  GCTRisk getInstance(WebDriver webDriver) {
        if(gctRisk == null) return new GCTRisk(webDriver);
        return  gctRisk;
    }

    public void create(Romm romm) {
        //Select tye abcotd or fsl
        try{
            if(romm.getType() == Romm.TYPE_ABCOTD) {
                click(eleRiskType.get(0));
            }
            if(romm.getType() == Romm.TYPE_FSL) {
                click(eleRiskType.get(1));
            }
            try{
                //Select associated abcotd
                waitSomeSecond(2);
                click(eleAssociatedAbcotds.findElement(By.cssSelector("input")));
                waitSomeSecond(1);
                WebElement abcotdsListBox = eleAssociatedAbcotds.findElement(By.cssSelector("div[role='listbox']"));
                waitForVisibleElement(abcotdsListBox, 20);
                WebElement eleAbcotdItem = abcotdsListBox.findElement(By.xpath("//div[contains(@class, 'item')]/span[text()='" + romm.getAssociateABCOTDs().getName() + "']"));
                waitForElementToBeClickable(eleAbcotdItem, 60);
                Assert.assertNotNull("The ABCOTD '" + romm.getAssociateABCOTDs().getName() + "' doesn't exist.", eleAbcotdItem);
                click(eleAbcotdItem);
                waitSomeSecond(0.5);
            }catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }

            //Type risk title
            eleRiskTitle.clear();
            eleRiskTitle.sendKeys(romm.getTitle());

            //Type risk description
            eleRiskDescription.clear();
            eleRiskDescription.sendKeys(romm.getDescription());

            //Select associated assertions
            try {
                click(eleAssociatedAssertions);
                WebElement assertionListBox = eleAssociatedAssertions.findElement(By.cssSelector("div[role='listbox']"));
                waitForVisibleElement(assertionListBox, 20);
                romm.getAssertion().forEach(a -> {
                    try{
                        WebElement assertionOption = assertionListBox.findElement(By.xpath("//div/div/label[text()='" + a.trim() + "']"));
                        click(assertionOption);
                    }catch (NoSuchElementException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                });
            }catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            waitSomeSecond(1);

            //Select risk level
            click(eleRiskClassification);
            waitSomeSecond(2);
            WebElement eleRiskLevel = eleRiskClassification.findElement(By.xpath("//div[contains(@class, 'menu')]/div[@name='" + romm.getClassification() + "']"));
            click(eleRiskLevel);

            //Type risk classification rationale
            eleRiskClassificationRationale.sendKeys(romm.getRiskClassificationRationale());

            //Save
            createOrSave();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
