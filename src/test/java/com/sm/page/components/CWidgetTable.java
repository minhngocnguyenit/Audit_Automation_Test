package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CWidgetTable extends PageInit {

    private WebElement ele;
    private WebDriver webDriver;

    public CWidgetTable(WebElement ele) {
        this.ele = ele;
    }

    private WebElement getAddNewRowBtn() {
        return ele.findElement(By.cssSelector("i.fa-plus"));
    }

    private WebElement getRemoveRowBtn() {
        return ele.findElement(By.cssSelector("i.fa-minus"));
    }

    public String getTitle() {
        return ele.findElement(By.cssSelector(".handsontable-top-title")).getText();
    }

    private WebElement getMasterTable() {
        return ele.findElement(By.cssSelector(".ht_master"));
    }

    public void type(List<List<String>> data) {
        WebElement table = getMasterTable();
        List<WebElement> trs = table.findElements(By.cssSelector("tbody tr"));
        int size = trs.size();
        try{
            WebElement eleTotal = ele.findElement(By.cssSelector(".total-amount"));
            if(eleTotal != null) {
                size--;
            }
        }catch (NoSuchElementException e) { }
        WebElement eleTextArea = null;
        for (int i = 0, i1 = 0; i < size && i1 < data.size(); i++, i1++) {
            WebElement tr = trs.get(i);
            List<WebElement> tds = tr.findElements(By.cssSelector("td.client-record-table"));
            List<String> dataRow = data.get(i1);
            for(int j = 0; j < tds.size(); j++) {
                WebElement td = tds.get(j);
                String value = dataRow.get(j);
                try {
                    click(td);
                    eleTextArea = ele.findElement(By.cssSelector(".handsontableInputHolder textarea.handsontableInput"));
                    eleTextArea.sendKeys(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(i1 == data.size() - 1) {
                    eleTextArea.sendKeys(Keys.TAB);
                }
            }
        }
    }

    public boolean isDisplayed(){
        return ele != null && ele.isDisplayed();
    }

    public void addNewRow(int newRow) {
        for(int i = 1; i <= newRow; i++) {
            click(getAddNewRowBtn());
            waitSomeSecond(0.5);
        }
    }

    public void removeRow(int[] index) {

    }

    public List<String> getHeader() {
        return null;
    }

    public String getTotalAmountValue() {
        WebElement eleTotal =ele.findElement(By.cssSelector(".total-amount span.value"));
        return eleTotal.getText();
    }
}
