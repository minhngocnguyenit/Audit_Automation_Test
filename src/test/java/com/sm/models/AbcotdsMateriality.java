package com.sm.models;

public class AbcotdsMateriality {
    private String abcotdName;
    private double determinedMateriality;
    private double determinedPM;

    public AbcotdsMateriality(String abcotdName, String determinedMateriality, String determinedPM) {
        this.abcotdName = abcotdName;
        this.determinedMateriality = Double.parseDouble(determinedMateriality);
        this.determinedPM = Double.parseDouble(determinedPM);
    }

    public String getAbcotdName() {
        return abcotdName;
    }

    public void setAbcotdName(String abcotdName) {
        this.abcotdName = abcotdName;
    }

    public double getDeterminedMateriality() {
        return determinedMateriality;
    }

    public void setDeterminedMateriality(double determinedMateriality) {
        this.determinedMateriality = determinedMateriality;
    }

    public double getDeterminedPM() {
        return determinedPM;
    }

    public void setGetDeterminedPM(double getDeterminedPM) {
        this.determinedPM = getDeterminedPM;
    }
}
