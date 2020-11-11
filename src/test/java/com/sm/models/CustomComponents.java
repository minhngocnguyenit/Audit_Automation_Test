package com.sm.models;

public class CustomComponents {

    private String abcotd;
    private String contentAreas;

    public CustomComponents(String abcotd, String contentAreas) {
        this.abcotd = abcotd;
        this.contentAreas = contentAreas;
    }

    public String getAbcotd() {
        return abcotd;
    }

    public void setAbcotd(String abcotd) {
        this.abcotd = abcotd;
    }

    public String getContentAreas() {
        return contentAreas;
    }

    public void setContentAreas(String contentAreas) {
        this.contentAreas = contentAreas;
    }
}
