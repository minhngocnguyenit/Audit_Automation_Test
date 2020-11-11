package com.sm.models;

public class RiskAssessmentRelateRommProcedure {
    private String room;
    private String procedure;
    private int timing;
    private String rollforward;

    public RiskAssessmentRelateRommProcedure(String room, String procedure, int timing, String rollforward) {
        this.room = room;
        this.procedure = procedure;
        this.timing = timing;
        this.rollforward = rollforward;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public String getRollforward() {
        return rollforward;
    }

    public void setRollforward(String rollforward) {
        this.rollforward = rollforward;
    }
}
