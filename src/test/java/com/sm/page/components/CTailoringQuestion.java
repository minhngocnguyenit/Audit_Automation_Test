package com.sm.page.components;
import com.sm.page.PageInit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CTailoringQuestion extends PageInit{

    public static final String YES_NO_CHECKLIST = "yes-no-checklist";

    private WebElement ele;
    private String tqHeading;
    private List<WebElement> tqAnswwering;


    public CTailoringQuestion(WebElement ele, WebDriver webDriver) {
        super(webDriver);
        this.ele = ele;
    }

    public boolean isDisPlay() {
        return ele != null || ele.isDisplayed();
    }

    public String getTqHeading() {
        tqHeading = ele.findElement(By.cssSelector(".field-description-heading  p")).getText();
        return tqHeading;
    }

    public void setTqHeading(String tqHeading) {
        this.tqHeading = tqHeading;
    }

    public List<WebElement> getTqAnswwering() {
        tqAnswwering = ele.findElements(By.cssSelector(".qna-subfield .field"));
        return tqAnswwering;
    }

    public void setTqAnswwering(List<WebElement> tqAnswwering) {
        this.tqAnswwering = tqAnswwering;
    }

    public String getQuestionType() {
        return null;
    }

    private WebElement getEleAnsweringByHeading(String answering) {
        return getTqAnswwering().stream().filter(ele -> answering.equals(ele.getText())).findFirst().orElse(null);
    }

    private WebElement getEleAnsweringByIndex(int index) {
        return getTqAnswwering().get(index);
    }

    public boolean answerTQ(String answering) {
        WebElement ele = this.getEleAnsweringByHeading(answering);
        if(ele == null){
            System.out.println("Can not find answering");
            return false;
        }
        WebElement child = ele.findElement(By.cssSelector(".checkbox"));
        if(ele.isEnabled() && !child.getAttribute("class").contains("checked")){
            WebElement lb = child.findElement(By.cssSelector("label"));
            click(lb);
        }

        return answering.equals(child.getAttribute("source-text")) && child.getAttribute("class").contains("checked");
    }

    public boolean answerTQ(int answerIndex) {
        WebElement ele = this.getEleAnsweringByIndex(answerIndex);
        if(ele == null)
            return false;
        if(ele.isEnabled()){
            scrollToElement(ele);
            ele.click();
        }

        WebElement child = ele.findElement(By.cssSelector(".checkbox"));
        return child.getAttribute("class").contains("checked");
    }

    private WebElement eleAnswerIsSelected() {
        List<WebElement> answering = this.getTqAnswwering();
        return answering.stream().filter(e->{
            WebElement option = e.findElement(By.cssSelector("div.checkbox"));
            String attr = attributeElement(option, "class");
            if(attr.contains("checked")) {
                return  true;
            }
            return false;
        }).findFirst().orElse(null);
    }

    public String getTextIsSelectedAnswer() {
        WebElement eleIsAnswerSelected = this.eleAnswerIsSelected();
        if(eleIsAnswerSelected != null) return  eleIsAnswerSelected.getText();
        return "";
    }

    public String getExternalLink() {
        String externalLink = "";
        try{
            wedDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebElement eleExternalInk = ele.findElement(By.cssSelector(".external-link"));
            externalLink = eleExternalInk.getText();
        }catch (NoSuchElementException e) {
        }
        wedDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return externalLink;
    }

    public boolean isDisabledSelected() {
        List<WebElement> answering = this.getTqAnswwering();
        int count = (int) answering.stream().map(e -> e.findElement(By.cssSelector(".checkbox"))).map(cb -> attributeElement(cb, "class")).filter(attrs -> attrs.contains("disabled")).count();
        return count == answering.size();
    }
}
