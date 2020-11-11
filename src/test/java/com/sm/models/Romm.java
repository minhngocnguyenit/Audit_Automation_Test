package com.sm.models;

import java.util.List;

public class Romm {

    public static final int TYPE_ABCOTD = 1;
    public static final int TYPE_FSL = 2;

    public static final String CLASSIFICATION_LOWER = "Lower";
    public static final String CLASSIFICATION_HIGHER = "Higher";
    public static final String CLASSIFICATION_REMOTE = "Remote";
    public static final String CLASSIFICATION_SIGNIFICANT = "Significant";

    private int type = TYPE_ABCOTD;
    private  String id;
    private String title;
    private String description;
    private ABCOTD associateABCOTDs;
    private List<String> assertion;
    private String classification;
    private List<Control> associateControls;
    private String riskClassificationRationale;

    public Romm() {

    }

    public Romm(String id){ this.id = id; }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ABCOTD getAssociateABCOTDs() {
        return associateABCOTDs;
    }

    public void setAssociateABCOTDs(ABCOTD associateABCOTDs) {
        this.associateABCOTDs = associateABCOTDs;
    }

    public List<String> getAssertion() {
        return assertion;
    }

    public void setAssertion(List<String> assertion) {
        this.assertion = assertion;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public List<Control> getAssociateControls() {
        return associateControls;
    }

    public void setAssociateControls(List<Control> associateControls) {
        this.associateControls = associateControls;
    }

    public String getRiskClassificationRationale() {
        return riskClassificationRationale;
    }

    public void setRiskClassificationRationale(String riskClassificationRationale) {
        this.riskClassificationRationale = riskClassificationRationale;
    }
}
