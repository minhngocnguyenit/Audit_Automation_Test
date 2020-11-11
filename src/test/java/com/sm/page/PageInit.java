package com.sm.page;

import com.sm.helper.KeyBoard;
import com.sm.helper.OSValidator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public abstract class PageInit{

    protected KeyBoard keyBoard;
    protected WebDriver wedDriver;
    protected String env;
    protected WebDriverWait webDriverWait;
    protected String engagementNamePrefix;

    public Locale locale = Locale.CANADA;

    @Rule
    protected ErrorCollector collector = new ErrorCollector();

    @FindBy(css = ".active.dimmer")
    protected WebElement eleLoading;

    @FindBy(css = ".auv-confirmationModal")
    protected WebElement eleConfirmModal;

    @FindBy(css=".auv-confirmationModal .cancelBtn")
    protected WebElement eleConfirmCancelBtn;

    @FindBy(css=".auv-confirmationModal .confirmDontSaveBtn")
    protected WebElement eleConfirmDontSaveBtn;

    @FindBy(css=".auv-confirmationModal .confirmBtn")
    protected WebElement eleConfirmSaveBtn;

    public PageInit(WebDriver driver) {
        this.wedDriver = driver;
        keyBoard = new KeyBoard();
        PageFactory.initElements(driver, this);
    }

    protected PageInit() {

    }

    public WebDriver getWedDriver() {
        return wedDriver;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    public void setEngagementNamePrefix(String prefix) { this.engagementNamePrefix = prefix; }
    public String getEngagementNamePrefix() { return this.engagementNamePrefix; }

    public void setWedDriver(WebDriver wedDriver) {
        this.wedDriver = wedDriver;
    }

    public void waitForDimmerDisappeared() {
        try{
            waitForVisibleElement(eleLoading, 10);
        }catch (Exception e) {
        }
        try{
            waitUtilElementHidden(eleLoading, 10);
        }catch (Exception e) {
        }
    }

    public void waitForElementInvisiable(WebElement ele) {
        this.webDriverWait = new WebDriverWait(this.wedDriver, 60);
        this.webDriverWait.until(ExpectedConditions.invisibilityOf(ele));
    }

    /**
     * Wait for element clickable
     * @param ele
     */
    public void waitForElementToBeClickable(WebElement ele) {
        this.webDriverWait = new WebDriverWait(this.wedDriver, 60);
        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public void waitForElementToBeClickable(WebElement ele, int timeout) {
        this.webDriverWait = new WebDriverWait(this.wedDriver, timeout);
        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    /**
     * Upload file
     * @param path
     */
    public void uploadFile(String path) {
        try {
            if(OSValidator.isMac()) {
                keyBoard.type(path);
                keyBoard.typeEnter();
                Thread.sleep(500);
                keyBoard.typeEnter();
            }else{
                StringSelection stringSelection = new StringSelection(path);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, stringSelection);
                keyBoard.paste();
                Thread.sleep(500);
                keyBoard.typeEnter();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wait for element visible
     * @param element
     */
    public void waitForVisibleElement(WebElement element, int timeOut) {
        this.webDriverWait = new WebDriverWait(this.wedDriver, timeOut);
        this.webDriverWait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return element.isDisplayed();
            }
        });
    }

    /**
     * Wait for text presented on an element
     * @param webElement
     * @param timeOut
     * @param text
     */
    public void waitUtilTextPresent(WebElement webElement, long timeOut, String text) {
        this.webDriverWait = new WebDriverWait(this.wedDriver, 60);
        this.webDriverWait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    /**
     * Wait for an element is hide
     * @param ele
     * @param timeOut
     */
    public void waitUtilElementHidden(WebElement ele, long timeOut) {
        this.webDriverWait = new WebDriverWait(this.wedDriver, timeOut);
        wedDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        this.webDriverWait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                try{
                    if(ele.isDisplayed()) return  false;
                }catch (Exception e){
                    return true;
                }
                return true;
            }
        });
        wedDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }


    /**
     * Scroll to bottom of an element
     * @param ele
     */
    public void scrollBottomOfElement(WebElement ele) {
        JavascriptExecutor js = ((JavascriptExecutor) this.wedDriver);
        js.executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight)", ele);
    }

    public List<String> listTabs(){
        return new ArrayList<String>(wedDriver.getWindowHandles());
    }

    /**
     * Switch to new tab
     * @param tabIndex
     */
    public void switchTab(int tabIndex) {
//        String tab = wedDriver.getWindowHandle();
//        wedDriver.switchTo().window(tab);
        List<String> lstTabs = this.listTabs();
        this.wedDriver.switchTo().window(lstTabs.get(tabIndex));
    }

    /**
     * Scroll to a presented element
     * @param ele
     */
    public void scrollToElement(WebElement ele) {
        JavascriptExecutor js = ((JavascriptExecutor) this.wedDriver);
        js.executeScript("arguments[0].scrollIntoView(true);", ele);

    }

    public void clickByJavaScript(WebElement ele) {
        ((JavascriptExecutor)wedDriver).executeScript("arguments[0].click();", ele);
    }

    public void click(WebElement ele) {
        try {
            ele.click();
        }catch (ElementNotInteractableException e){
//            System.out.println(e.getMessage());
            try {
                waitUtilElementHidden(eleLoading, 60);
            }catch (TimeoutException e1){
//                System.out.println(e1.getMessage());
            }
            clickByJavaScript(ele);
        }catch (Exception e2) {
//            System.out.println(e2.getMessage());
            scrollToElement(ele);
            ele.click();
        }
    }

    public void doubleClick(WebElement ele) {
        Actions action = new Actions(this.wedDriver);
        action.doubleClick(ele).perform();
    }

    public void refresh(){
        wedDriver.navigate().refresh();
    }

    public String getBeforeContent(WebElement e) {
        String script = "return window.getComputedStyle(arguments[0],':before').getPropertyValue('content')";
        JavascriptExecutor js = (JavascriptExecutor) wedDriver;
        String content = (String) js.executeScript(script, e);
        return content;
    }

    public void waitSomeSecond(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String attributeElement(WebElement e, String attr) {
        return e.getAttribute(attr);
    }

    public void hover(WebElement ele) {
        Actions actions = new Actions(wedDriver);
        actions.moveToElement(ele).perform();
    }
}
