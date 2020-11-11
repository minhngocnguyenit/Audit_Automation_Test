package com.sm.models;

import java.util.List;

public class Control {

    public static final int TYPE_ABCOTD = 1;
    public static final int TYPE_FSL = 2;

    private int associatedWith;
    private String controlId;
    private String title;
    private boolean isRelevant;
    private boolean isAutomated;
    private String description;
    private List<ABCOTD> associateAbcotds;
    private String frequency;
    private String approach;
    private String type;
    private List<Romm> associateROMMs;
    private String designConclusion;
    private String implementationConclusion;
    private String oeTestingStrategy;
    private String oeDateLastTested;
    private String oeConclusion;
    private String nature = "Manual";

    public int getAssociatedWith() {
        return associatedWith;
    }

    public void setAssociatedWith(int associatedWith) {
        this.associatedWith = associatedWith;
    }

    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tile) {
        this.title = tile;
    }

    public boolean isRelevant() {
        return isRelevant;
    }

    public void setRelevant(boolean relevant) {
        isRelevant = relevant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ABCOTD> getAssociateAbcotds() {
        return associateAbcotds;
    }

    public void setAssociateAbcotds(List<ABCOTD> associateAbcotds) {
        this.associateAbcotds = associateAbcotds;
    }

    public String getFrequency() {
        if(this.frequency == null|| this.frequency.isEmpty()) frequency = "-";
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getApproach() {
        if(this.approach == null || this.approach.isEmpty()) approach = "-";
        return approach;
    }

    public void setApproach(String approach) {
        this.approach = approach;
    }

    public String getType() {
        if(type == null || type.isEmpty()) type = "-";
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Romm> getAssociateROMMs() {
        return associateROMMs;
    }

    public void setAssociateROMMs(List<Romm> associateROMMs) {
        this.associateROMMs = associateROMMs;
    }

    public String getDesignConclusion() {
        if(designConclusion == null || designConclusion.isEmpty()) designConclusion = "-";
        return designConclusion;
    }

    public void setDesignConclusion(String designConclusion) {
        this.designConclusion = designConclusion;
    }

    public String getImplementationConclusion() {
        if(implementationConclusion == null || implementationConclusion.isEmpty()) implementationConclusion = "-";
        return implementationConclusion;
    }

    public void setImplementationConclusion(String implementationConclusion) {
        this.implementationConclusion = implementationConclusion;
    }

    public String getOeTestingStrategy() {
        if(this.oeTestingStrategy == null || this.oeTestingStrategy.isEmpty()) oeTestingStrategy = "-";
        return oeTestingStrategy;
    }

    public void setOeTestingStrategy(String oeTestingStrategy) {
        this.oeTestingStrategy = oeTestingStrategy;
    }

    public String getOeDateLastTested() {
        if(oeDateLastTested == null || oeDateLastTested.isEmpty()) oeDateLastTested = "-";
        return oeDateLastTested;
    }

    public void setOeDateLastTested(String oeDateLastTested) {
        this.oeDateLastTested = oeDateLastTested;
    }

    public String getOeConclusion() {
        if(oeConclusion == null || oeConclusion.isEmpty()) oeConclusion = "-";
        return oeConclusion;
    }

    public void setOeConclusion(String oeConclusion) {
        this.oeConclusion = oeConclusion;
    }

    public String getNature() {
        if(isAutomated) {
            return "Automated";
        }
        return "Manual";
    }

    public void setNature(String nature) {
        if(nature.equalsIgnoreCase("Automated")) isAutomated = true;
        else isAutomated = false;
        this.nature = nature;
    }

    public boolean isAutomated() {
        return isAutomated;
    }

    public void setAutomated(boolean automated) {
        isAutomated = automated;
    }
}
