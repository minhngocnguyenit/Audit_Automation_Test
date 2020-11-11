package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CWidgetAccountBalanceView extends PageInit {

    @FindBys(@FindBy(css="tbody tr"))
    private List<WebElement> eleListAccountBalance;


    private WebElement ele;
    public CWidgetAccountBalanceView(WebElement ele, WebDriver webDriver) {
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

    public void select(List<List<String>> accounts) {
        for (List<String> account : accounts) {
            WebElement ele = this.eleListAccountBalance.stream().filter(e -> {
                boolean rs = false;
                WebElement eleName = e.findElement(By.cssSelector("tbody tr> td:nth-child(4)"));
                if(eleName != null && eleName.getText().equals(account)) {
                    rs = true;
                }
                return rs;
            }).findFirst().orElse(null);

            if(ele == null) continue;
            WebElement cb = ele.findElement(By.cssSelector(".htCheckboxRendererInput"));
            if(cb == null || !cb.isEnabled()) continue;
            click(cb);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
