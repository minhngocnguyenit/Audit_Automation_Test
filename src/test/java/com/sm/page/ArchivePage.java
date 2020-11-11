package com.sm.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArchivePage extends PageInit {

    private static final String STEP_REVIEW_ENGAGEMENT_DETAIL = "Review the engagement details";
    private static final String STEP_REVIEW_TEAM_HISTORY = "Review the team history";
    private static final String STEP_SELECT_RELEVANT_AND_ENGAGEMENT_OPERATIONS = "Select relevant end engagement operations";

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static ArchivePage archivePage;

    @FindBy(css = ".archival-wizard")
    private WebElement eleArchiveWizard;

    @FindBy(css = ".archival-step-description h3")
    private WebElement eleStepDescription;

    @FindBy(css = ".archival-step-actions .btn-next")
    private WebElement eleBtnNext;

    @FindBy(css = ".auv-ApproveModal")
    private WebElement eleApproveArchiveModal;

    @FindBy(css = ".auv-ApproveModal .btn-update")
    private WebElement eleConfirmApproveArchive;

    public ArchivePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static ArchivePage getInstance(WebDriver webDriver) {
        if(archivePage == null) return new ArchivePage(webDriver);
        return archivePage;
    }

    public void processArchive(){
        //
        waitUtilTextPresent(eleStepDescription, 30, STEP_REVIEW_ENGAGEMENT_DETAIL);
        logger.info("-- I click Next on '" + STEP_REVIEW_ENGAGEMENT_DETAIL + "'");
        waitForElementToBeClickable(eleBtnNext,5);
        click(eleBtnNext);

        waitUtilTextPresent(eleStepDescription, 30, STEP_REVIEW_TEAM_HISTORY);
        logger.info("-- I click Next on '" + STEP_REVIEW_TEAM_HISTORY + "'");
        waitForElementToBeClickable(eleBtnNext,5);
        click(eleBtnNext);

        waitUtilTextPresent(eleStepDescription, 30, STEP_SELECT_RELEVANT_AND_ENGAGEMENT_OPERATIONS);
        logger.info("-- I click Next on '" + STEP_SELECT_RELEVANT_AND_ENGAGEMENT_OPERATIONS + "'");
        waitForElementToBeClickable(eleBtnNext,5);
        click(eleBtnNext);
        waitForDimmerDisappeared();
    }

    public void processApproveArchive() {
        waitUtilTextPresent(eleStepDescription, 30, STEP_REVIEW_ENGAGEMENT_DETAIL);
        logger.info("-- I click Next on '" + STEP_REVIEW_ENGAGEMENT_DETAIL + "'");
        waitForElementToBeClickable(eleBtnNext,5);
        click(eleBtnNext);

        waitUtilTextPresent(eleStepDescription, 30, STEP_REVIEW_TEAM_HISTORY);
        logger.info("-- I click Next on '" + STEP_REVIEW_TEAM_HISTORY + "'");
        waitForElementToBeClickable(eleBtnNext,5);
        click(eleBtnNext);

        waitUtilTextPresent(eleStepDescription, 30, STEP_SELECT_RELEVANT_AND_ENGAGEMENT_OPERATIONS);
        logger.info("-- I click Next on '" + STEP_SELECT_RELEVANT_AND_ENGAGEMENT_OPERATIONS + "'");
        waitForElementToBeClickable(eleBtnNext,5);
        click(eleBtnNext);

        waitForVisibleElement(eleApproveArchiveModal, 10);
        logger.info("-- I click on confirm approve archive");
        click(eleConfirmApproveArchive);

        waitUtilElementHidden(eleArchiveWizard, 90);
    }
}
