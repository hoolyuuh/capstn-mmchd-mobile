package com.idsr.mobile.models;

public class CaseForm {
    private Case cases;
    private Patient patient;
    private RiskFactors riskFactors;
    private CaseData caseData;

    public CaseForm() {
    }

    public Case getCases() {
        return cases;
    }

    public void setCases(Case cases) {
        this.cases = cases;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public RiskFactors getRiskFactors() {
        return riskFactors;
    }

    public void setRiskFactors(RiskFactors riskFactors) {
        this.riskFactors = riskFactors;
    }

    public CaseData getCaseData() {
        return caseData;
    }

    public void setCaseData(CaseData caseData) {
        this.caseData = caseData;
    }
}
