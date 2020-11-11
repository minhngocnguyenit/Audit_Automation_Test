package com.sm.models;

public class RiskAssessmentRelateProcedureAccount {

    private String abcotd;
    private String procedure;
    private String account;

    public RiskAssessmentRelateProcedureAccount(String abcotd, String procedure, String account) {
        this.abcotd = abcotd;
        this.procedure = procedure;
        this.account = account;
    }

    public String getAbcotd() {
        return abcotd;
    }

    public void setAbcotd(String abcotd) {
        this.abcotd = abcotd;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
