package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CVerticalTable extends PageInit {

    private WebElement ele;
    public CVerticalTable(WebElement ele) {
        this.ele = ele;
    }

    public boolean isDisplayed() {
        return ele != null && ele.isDisplayed();
    }

    public void addNewRow() {
        //TODO
    }

    public void removeRow(int[] rIndex){
        //TODO
    }

    public void type( String header, int row, String text) {
        //TODO
    }

    public void type(int col, int row, String text) {
        //TODO
    }

    public void type(List<List<String>> data) {

    }

    public List<String> header() {
        //TODO
        return null;
    }
}
