package com.sm.page;

import com.sm.models.AbcotdsMateriality;
import com.sm.page.components.CTailoringQuestion;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MaterialityPage extends BaseWorkingPaperPage{

        private Logger logger = Logger.getLogger(MaterialityPage.class.getName());
        private static MaterialityPage materialityPage;

        public MaterialityPage(WebDriver driver) {
            super(driver);
        }

        public static MaterialityPage getInstance(WebDriver webDriver) {
            if (materialityPage == null) return new MaterialityPage(webDriver);
            return materialityPage;
        }

        @FindBy(css=".left-planning-nav-scrolling .menu .sub-section-active a:nth-child(1)")
        WebElement eleMaterialityPlanning;

        @FindBy(xpath="//label[contains(.,'Trial Balance data')]")
        WebElement eleTrialBalanceData;

        @FindBy(xpath = "//div[5]/div/div/div/div/div/div/div/div/input")
        WebElement eleAmountMateriality;

        @FindBy(name = "Percentage")
        WebElement elePercentage;

        @FindBy(name = "Factor used for CTT")
        WebElement eleAmountCTT;

        @FindBy(xpath = "//div[4]/div[3]/div/div/div/div/div/input")
        WebElement eleDeterminedCTT;

        @FindBy(css = ".qna-field.question:nth-child(2)")
        private WebElement eleTQDetermineMateriality;

        @FindBy(css = ".cloning-question .abcotd-table")
        private WebElement eleCloneQuestionABCOTDTable;

        @FindBy(css = ".cloning-question .abcotd-table input.search")
        private List<WebElement> eleLstAbcotds;

        @FindBy(css = ".cloning-question .abcotd-determined input[name='Determined materiality']")
        private List<WebElement> eleLstAbcotdsDeterminedMateriality;

        @FindBy(css = ".cloning-question .abcotd-determined input[name='Determined PM']")
        private List<WebElement> eleLstAbcotdsPMMateriality;

        @FindBy(css = ".additional-button")
        private WebElement eleAddAbcotdMaterialityBtn;

        private CTailoringQuestion tqAbcotdMateriality;

        public boolean isLoad() {
            waitForVisibleElement(this.eleMaterialityPlanning, 120);
            waitForElementToBeClickable(this.eleMaterialityPlanning);
            return this.eleMaterialityPlanning.isDisplayed();
        }

        public void setupMateriality(double overall, double pm, double ctt) {
            logger.info("-- I type overall materiality");
            this.eleAmountMateriality.clear();
            this.eleAmountMateriality.sendKeys(String.valueOf(overall));

            logger.info("-- I type percent performance materiality");
            waitForElementToBeClickable(this.elePercentage);
            this.elePercentage.clear();
            this.elePercentage.sendKeys(String.valueOf(pm));

            logger.info("-- I type CTT materiality");
            waitForElementToBeClickable(this.eleDeterminedCTT);
            this.eleDeterminedCTT.clear();
            this.eleDeterminedCTT.sendKeys(String.valueOf(ctt));
        }

        public void enterABCOTDsMateriality(List<AbcotdsMateriality> data) {
            logger.info("-- I answer Yes to Tailoring ABCOTD Materiality");
//            tqAbcotdMateriality = new CTailoringQuestion(this.eleTQDetermineMateriality, wedDriver);
//            tqAbcotdMateriality.answerTQ("Yes");
            waitForVisibleElement(eleCloneQuestionABCOTDTable, 10);
            int size = data.size();
            for (int i = 0; i < size && i < eleLstAbcotds.size(); i++) {
                WebElement eleAbcotds = this.eleLstAbcotds.get(i);
                WebElement eleDeterminedMateriality = this.eleLstAbcotdsDeterminedMateriality.get(i);
                WebElement eleDeterminedPM = this.eleLstAbcotdsPMMateriality.get(i);

                logger.info("-- I type ABCOTD '" + data.get(i).getAbcotdName() + "'");
                eleAbcotds.sendKeys(data.get(i).getAbcotdName());
                waitSomeSecond(1);
                WebElement ele = wedDriver.findElement(By.xpath("//div[contains(@class, 'selected')]/span[text()='" + data.get(i).getAbcotdName() + "']"));
                waitForVisibleElement(ele, 120);
                eleAbcotds.sendKeys(Keys.ENTER);
                waitForElementToBeClickable(eleDeterminedMateriality);
                waitForElementToBeClickable(eleDeterminedPM);

                logger.info("-- I type value Determine Materiality");
                eleDeterminedMateriality.clear();
                eleDeterminedMateriality.sendKeys(String.valueOf(data.get(i).getDeterminedMateriality()));

                logger.info("-- I type value Performance Materiality");
                eleDeterminedPM.clear();
                eleDeterminedPM.sendKeys(String.valueOf(data.get(i).getDeterminedPM()));
                if(size > 1 && i < size - 1){
                    logger.info("-- I click on Add ABCOTDs Materiality button");
                    click(eleAddAbcotdMaterialityBtn);
                    waitForDimmerDisappeared();
                    waitForElementToBeClickable(this.eleAddAbcotdMaterialityBtn, 30);
                    PageFactory.initElements(wedDriver, eleLstAbcotds);
                }
            }
        }
}