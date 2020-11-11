package com.sm.page.gct;

import com.sm.models.Control;
import com.sm.models.Romm;
import com.sm.page.PageInit;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class GCTControl extends GeneralCommonTool {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static GCTControl gctControl;

    @FindBy(css = ".create-link-common")
    private WebElement ele;

    @FindBys(@FindBy(css = ".create-link-common .cls-associated-width .field .checkbox label"))
    private List<WebElement> eleControlType;

    @FindBy(css = ".create-link-common div[name='abcotdIds']")
    private WebElement eleAssociatedAbcotds;

    @FindBy(xpath = "//form[contains(@class,'create-link-common')]/div/div[contains(@class, 'row') and contains(@class, 'toggle-common')]/div/div[input[@name='isRelevant']]")
    private WebElement eleIsRelevant;

    @FindBy(css = ".create-link-common input[name='title']")
    private WebElement eleTitle;

    @FindBy(css = ".create-link-common textarea[name='description']")
    private WebElement eleDescription;

    @FindBy(css = ".create-link-common div[name='frequency']")
    private WebElement eleFrequency;

    @FindBy(css = ".create-link-common div[name='approach']")
    private WebElement eleApproach;

    @FindBy(css = ".create-link-common div[name='type']")
    private WebElement eleType;

    @FindBy(css = ".create-link-common .btn-associated")
    private WebElement eleManageAssociateROMMs;

    @FindBy(css = ".create-link-common div[name='designConclusion']")
    private WebElement eleDesignConclusion;

    @FindBy(css = ".create-link-common div[name='implementationConclusion']")
    private WebElement eleImplementationConclusion;

    @FindBy(css = ".create-link-common div[name='oeTestingStrategy']")
    private WebElement eleOETestingStrategy;

    @FindBy(css = ".create-link-common div[name='oeDateLastTested']")
    private WebElement eleOEDateLastTested;

    @FindBy(css = ".create-link-common div[name='oeConclusion']")
    private WebElement eleOEConclusion;

    @FindBy(xpath = "//form[contains(@class,'create-link-common')]/div/div[contains(@class, 'row') and contains(@class, 'toggle-common')]/div/div[input[@name='automated']]")
    private WebElement eleIsAutomated;

    @FindBy(css = ".associated-modal")
    private WebElement eleAssociateModal;

    @FindBy(css = ".associated-modal .associated-filter")
    private WebElement eleFilter;

    @FindBy(css = ".associated-modal .associated-table tbody tr")
    private List<WebElement> eleAssociateROMMDataRows;

    @FindBy(css = ".associated-modal .btn-update-asociation")
    private WebElement eleUpdateAssociation;

    public GCTControl(WebDriver webDriver) {
        super(webDriver);
    }

    public static GCTControl getInstance(WebDriver webDriver) {
        if(gctControl == null) return new GCTControl(webDriver);
        return gctControl;
    }

    private void selectValueOnDropDown(WebElement eleDropDown, String value) {
        if(value.isEmpty()) return;
        WebElement alert = eleDropDown.findElement(By.cssSelector("div.text"));
        clickByJavaScript(eleDropDown);
        WebElement listBox = eleDropDown.findElement(By.cssSelector("div.menu"));
        waitForVisibleElement(listBox, 5);

        if(listBox.isDisplayed()) {
            List<WebElement> eleListOptions = listBox.findElements(By.cssSelector("div.item"));
            WebElement option = eleListOptions.stream().filter(e->value.equalsIgnoreCase(e.getText())).findFirst().orElse(null);
            Assert.assertNotNull("Value '" + value + "' is not exist.", option);
            if(option != null){
                clickByJavaScript(option);
                waitSomeSecond(0.5);
            }
        }
    }

    public void create(Control control) {
        if(control.getAssociatedWith() == Control.TYPE_ABCOTD) {
            click(eleControlType.get(0));
        }

        if(control.getAssociatedWith() == Control.TYPE_FSL) {
            click(eleControlType.get(1));
        }
        //Select associate abcotds
        click(eleAssociatedAbcotds);
        waitSomeSecond(1.5);
        WebElement assertionListBox = eleAssociatedAbcotds.findElement(By.cssSelector("div[role='listbox']"));
        waitForVisibleElement(assertionListBox, 20);
        control.getAssociateAbcotds().forEach(a -> {
            try{
                WebElement abcotd = assertionListBox.findElement(By.xpath("//div/div/label[text()='" + a.getName().trim() + "']"));
                waitForElementToBeClickable(abcotd, 10);
                click(abcotd);
            }catch (NoSuchElementException | TimeoutException e) {
                e.printStackTrace();
            }
        });

        WebElement abcotdInput = eleAssociatedAbcotds.findElement(By.cssSelector("input.search"));
        abcotdInput.sendKeys(Keys.TAB);
        waitSomeSecond(1);

        //Select relevant
        if(control.isRelevant()) click(eleIsRelevant);

        //Type control title
        eleTitle.clear();
        eleTitle.sendKeys(control.getTitle());

        //Type description
        eleDescription.clear();
        eleDescription.sendKeys(control.getDescription());

        //select automated
        if(control.isAutomated()) click(eleIsAutomated);

        //Select Frequency
        selectValueOnDropDown(eleFrequency, control.getFrequency());

        //Select approach
        selectValueOnDropDown(eleApproach, control.getApproach());

        //Select type
        selectValueOnDropDown(eleType, control.getType());

        //Map associate ABCOTDs
        if(control.getAssociateROMMs().size() > 0) {
            click(eleManageAssociateROMMs);
            waitForVisibleElement(eleAssociateModal, 5);
            for(Romm r: control.getAssociateROMMs()) {
                String rommID = r.getId();
                WebElement eleRomm = eleAssociateROMMDataRows.stream().filter(e-> {
                    try{
                        WebElement td = e.findElement(By.cssSelector("td:nth-child(2)"));
                        if(rommID.equalsIgnoreCase(td.getText())) {
                            return true;
                        }
                    }catch (NoSuchElementException ex) {

                    }
                    return false;
                }).findFirst().orElse(null);

                if(eleRomm != null) {
                    WebElement cb = eleRomm.findElement(By.cssSelector("td:nth-child(1) input[type='checkbox']"));
                    click(cb);
                }
            }

            waitForElementToBeClickable(eleUpdateAssociation, 2);
            click(eleUpdateAssociation);
            waitUtilElementHidden(eleAssociateModal, 5);
        }

        //Select design conclusion
        selectValueOnDropDown(eleDesignConclusion, control.getDesignConclusion());

        //Select implementation conclusion
        selectValueOnDropDown(eleImplementationConclusion, control.getImplementationConclusion());

        //Select testing strategy
        selectValueOnDropDown(eleOETestingStrategy, control.getOeTestingStrategy());

        //select oe date last tested
        selectValueOnDropDown(eleOEDateLastTested, control.getOeDateLastTested());

        //select oe conclusion
        selectValueOnDropDown(eleOEConclusion, control.getOeConclusion());

        createOrSave();
    }
}
