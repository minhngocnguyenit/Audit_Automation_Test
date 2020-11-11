package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CWidgetTestingTable extends PageInit {

    private WebElement ele;

    @FindBy(css = ".wdg-failure-documentation")
    private WebElement eleFailureDocumentationModal;

    @FindBy(css = ".wdg-failure-documentation .reasonfail")
    private WebElement eleReasonFail;

    @FindBy(css = ".wdg-failure-documentation .btn-save")
    private WebElement eleBtnSave;

    @FindBy(css = ".wdg-failure-documentation table tbody tr")
    private List<WebElement> eleListTestType;

    public CWidgetTestingTable(WebElement ele, WebDriver webDriver) {
        super(webDriver);
        this.ele = ele;
    }

    private WebElement getMasterTable() {
        return ele.findElement(By.cssSelector(".ht_master"));
    }

    /**
     * Find eleOption in a dropdown
     * @param option option value
     * @return
     */
    private WebElement findElementInDropdown(String option) {
        WebElement eleOption = null;
        try{
            eleOption = ele.findElement(By.xpath("//div[contains(@class, 'handsontableEditor')]//td[text()='" + option + "']"));
        }catch (Exception e) {

        }
        return eleOption;
    }

    private WebElement findCheckboxElementInCell(WebElement td) {
        return td.findElement(By.cssSelector("input.htCheckboxRendererInput"));
    }

    public void type(List<List<String>> data) {
        WebElement table = getMasterTable();
        List<WebElement> trs = table.findElements(By.cssSelector("tbody tr"));
        int temp = 0;
        for (int i = 1, i1 = 0; i < trs.size() - 1 && i1 < data.size(); i++, i1++){
            WebElement tr = trs.get(i);
            List<WebElement> tds = tr.findElements(By.cssSelector("td"));
            List<String> dataRow = data.get(i1);
            for(int j = 4; j < tds.size(); j++) {
                WebElement td = tds.get(j);
                try {
                    String cellData = dataRow.get(j - 4);
                    if(isDropDownCell(td)) {
                        try{
                            doubleClick(td);
                            if(temp > 0) click(td);
                            WebElement option = findElementInDropdown(cellData);
                            if(option != null) {
                                click(option);
                                if(cellData.equals("No")) {

                                }
                                if(cellData.equals("Yes")) {
                                    waitForVisibleElement(eleFailureDocumentationModal, 5);
                                    //Select all tests
                                    if(eleListTestType.size() > 0) {
                                        WebElement lastTestType = eleListTestType.get(eleListTestType.size() - 1);
                                        WebElement cb = lastTestType.findElement(By.tagName("label"));
                                        click(cb);
                                    }
                                    //Type on reason failure
                                    eleReasonFail.sendKeys("Reason");
                                    waitForElementToBeClickable(eleBtnSave, 5);
                                    click(eleBtnSave);
                                    waitUtilElementHidden(eleFailureDocumentationModal, 10);
                                }
                            }
                        }catch (Exception e) {
                            
                        }
                    } else if(isCheckboxCell(td)) {
                        boolean value = Boolean.parseBoolean(cellData);
                        WebElement cb = findCheckboxElementInCell(td);
                        if(value && !attributeElement(td, "class").contains("checkbox-checked")) {
                            click(cb);
                        }
                    } else {
                        typeOnCell(td, cellData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            temp++;
        }
    }

    private void typeOnCell(WebElement td, String text) {
        wedDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try{
            doubleClick(td);
            WebElement eleTextAreas = ele.findElement(By.cssSelector(".handsontableInputHolder[style*='opacity: 1'] textarea.handsontableInput"));
            eleTextAreas.clear();
            eleTextAreas.sendKeys(text);
        }catch (NoSuchElementException e){
            doubleClick(td);
            WebElement eleTextAreas = ele.findElement(By.cssSelector(".handsontableInputHolder[style*='opacity: 1'] textarea.handsontableInput"));
            eleTextAreas.clear();
            eleTextAreas.sendKeys(text);
        }
        wedDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    private boolean isCheckboxCell(WebElement eleTd) {
        boolean rs = false;
        wedDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try{
            WebElement lbCb = eleTd.findElement(By.cssSelector(".htCheckboxRendererLabel"));
            rs =  lbCb.isDisplayed();
        }catch (NoSuchElementException e) {

        }
        wedDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return rs;
    }

    private boolean isDropDownCell(WebElement eleTd) {
        return eleTd.getAttribute("class").contains("htAutocomplete");
    }
}
