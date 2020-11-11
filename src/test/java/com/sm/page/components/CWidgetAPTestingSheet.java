package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CWidgetAPTestingSheet extends PageInit {

    private WebElement ele;
    public CWidgetAPTestingSheet(WebElement ele, WebDriver webDriver) {
        super(webDriver);
        this.ele = ele;
    }

    public boolean isDisplayed() {
        try{
            return ele.isDisplayed();
        }catch (NoSuchElementException e) {

        }
        return  false;
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
        WebElement eleTable = getMasterTable();
        List<WebElement> trs = eleTable.findElements(By.cssSelector("tbody tr"));
        for(int i = 0, i1 = 0; i < trs.size() && i1 < data.size(); i++, i1++) {
            WebElement tr = trs.get(i);
            List<WebElement> tds = tr.findElements(By.cssSelector("td"));
            List<String> cellRow = data.get(i1);
            for(int j = 0; j < tds.size(); j++) {
                String cellData = cellRow.get(j);
                WebElement td = tds.get(j);
                if(isDropDownCell(td)){
                    try{
                        click(td);
                        waitSomeSecond(0.3);
                        doubleClick(td);
                        WebElement option = findElementInDropdown(cellData);
                        if(option != null) {
                            click(option);
                        }
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }else {
                    typeOnCell(td, cellData);
                }
            }
        }
    }

    private boolean isDropDownCell(WebElement eleTd) {
        return eleTd.getAttribute("class").contains("htAutocomplete");
    }

    private void typeOnCell(WebElement td, String text) {
        wedDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
}
