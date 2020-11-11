package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CWidgetARTestingSheet extends PageInit {

    private WebElement ele;

    public CWidgetARTestingSheet(WebElement ele, WebDriver webDriver) {
        super(webDriver);
        this.ele = ele;
    }

    public boolean isDisplayed() {
        return ele.isDisplayed();
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
        }catch (Exception ignored) {

        }
        return eleOption;
    }

    private WebElement findCheckboxElementInCell(WebElement td) {
        return td.findElement(By.cssSelector("input.htCheckboxRendererInput"));
    }

    public void type(List<List<String>> data) {
        WebElement eleTable = getMasterTable();
        List<WebElement> trs = eleTable.findElements(By.cssSelector("tbody tr"));
        for(int i = 0, i1 = 0; i < trs.size() - 1 && i1 < data.size(); i++, i1++) {
            WebElement tr = trs.get(i);
            List<String> dataRow = data.get(i1);
            List<WebElement> tds = tr.findElements(By.cssSelector("td"));
            int temp = 0;
            for (int j = 0; j < tds.size(); j++) {
                try{
                    WebElement td = tds.get(j);
                    String cellData = dataRow.get(j - temp);
                    if(isDisableCell(td)) {
                        temp++;
                    }else if(isDropDownCell(td)){
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
                    } else if(isCheckboxCell(td)) {
                        click(td);
                        boolean value = Boolean.parseBoolean(cellData);
                        WebElement lb = findCheckboxElementInCell(td);
                        String dataValue = td.findElement(By.cssSelector("div.custom-checkbox div")).getAttribute("data-value");
                        if(value && "no".equals(dataValue)) {
                            lb.sendKeys(Keys.SPACE);
                        }
                    }else{
                        typeOnCell(td, cellData);
                    }
                }catch (Exception e) {
                    // e.printStackTrace();
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
