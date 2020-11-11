package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CWidgetSampleSelection extends PageInit {

    private WebElement ele;
    public CWidgetSampleSelection(WebElement ele) {
        this.ele = ele;
    }

    public boolean isEmpty() {
        WebElement table = ele.findElements(By.cssSelector(".handson-table-container table")).get(0);
        List<WebElement> trs = table.findElements(By.cssSelector("tbody tr"));
        return ele.isDisplayed() && trs.size() == 0;
    }

    private WebElement getMasterTable() {
        return ele.findElement(By.cssSelector(".ht_master"));
    }

    public void type(List<List<String>> data) {
        WebElement table = getMasterTable();
        List<WebElement> trs = table.findElements(By.cssSelector("tbody tr"));

        for (int i = 0; i < trs.size() && i < data.size(); i++) {
            WebElement tr = trs.get(i);
            List<WebElement> tds = tr.findElements(By.cssSelector("td.sample-table"));
            List<String> dataRow = data.get(i);
            for (int j = 0; j < tds.size(); j++) {
                WebElement td = tds.get(j);
                click(td);
                waitSomeSecond(0.3);
                WebElement eleTextArea = ele.findElement(By.cssSelector(".handsontableInputHolder textarea.handsontableInput"));
                eleTextArea.sendKeys(dataRow.get(j));
            }
        }
    }
}
