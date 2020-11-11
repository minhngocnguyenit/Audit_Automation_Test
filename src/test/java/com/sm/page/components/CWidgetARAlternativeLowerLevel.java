package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CWidgetARAlternativeLowerLevel extends PageInit {

    private WebElement ele;

    public CWidgetARAlternativeLowerLevel(WebElement ele, WebDriver webDriver) {
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
        int size = trs.size();
        if(size > 1) {
            size--;
        }
        for (int i = 0, i1 = 0; i < size && i1 < data.size(); i++,i1++) {
            WebElement tr = trs.get(i);
            List<WebElement> tds = tr.findElements(By.cssSelector("td"));
            List<String> dataRow = data.get(i1);
            int temp = 0;
            for (int j = 4; j < tds.size(); j++) {
                WebElement td = tds.get(j);
                try {
                    String cellData = dataRow.get(j - temp - 4);
                    if(isDisableCell(td)) {
                        temp++;
                    } else if(isDropDownCell(td)) {
                        try{
                            doubleClick(td);
                            WebElement option = findElementInDropdown(cellData);
                            if(option != null) {
                                click(option);
                            }
                        }catch (NoSuchElementException e) {

                        }
                    } else if(isCheckboxCell(td)) {
                        boolean value = Boolean.parseBoolean(cellData);
                        WebElement cb = findCheckboxElementInCell(td);
                        if(value && !td.getAttribute("class").contains("checkbox-checked")) {
                            click(cb);
                        }
                    } else {
                        typeOnCell(td, cellData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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

    private boolean isCheckboxCell(WebElement eleTd) {
        String attrs = eleTd.getAttribute("class");
        return !attrs.contains("col-amount") && !attrs.contains("htAutocomplete") && attrs.contains("htCenter");
    }

    private boolean isDropDownCell(WebElement eleTd) {
        return eleTd.getAttribute("class").contains("htAutocomplete");
    }

    private boolean isDisableCell(WebElement eleId) {
        return eleId.getAttribute("class").contains("color-bank-statement");
    }
}
