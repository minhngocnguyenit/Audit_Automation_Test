package com.sm.models;

import java.util.List;

public class TailoringQuestion {
    private String id;
    private String header;
    private String answered;
    private List<String> lstAnswers;

    public TailoringQuestion(){

    }

    public TailoringQuestion(String id, String answered) {
        this.id = id;
        this.answered = answered;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public List<String> getLstAnswers() {
        return lstAnswers;
    }

    public void setLstAnswers(List<String> lstAnswers) {
        this.lstAnswers = lstAnswers;
    }
}
