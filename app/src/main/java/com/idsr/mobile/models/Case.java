package com.idsr.mobile.models;

public class Case {
	private String caseID;
	private String diseaseID;
	private String reportedBy;
	private String caseLevel;
	private String reportDate;
	private String investigationDate;
	private String dateAdmitted;
	private String dateOnset;
	private String reporterName;
	private String reporterContact;
	private String investigatorLab;
	private String investigatorName;
	private String investigatorContact;
	private String finalDiagnosis;
	private String CRFID;

	public Case() {
	}

	public String getCaseID() {
		return caseID;
	}

	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	public String getDiseaseID() {
		return diseaseID;
	}

	public void setDiseaseID(String diseaseID) {
		this.diseaseID = diseaseID;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public String getCaseLevel() {
		return caseLevel;
	}

	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getInvestigationDate() {
		return investigationDate;
	}

	public void setInvestigationDate(String investigationDate) {
		this.investigationDate = investigationDate;
	}

	public String getDateAdmitted() {
		return dateAdmitted;
	}

	public void setDateAdmitted(String dateAdmitted) {
		this.dateAdmitted = dateAdmitted;
	}

	public String getDateOnset() {
		return dateOnset;
	}

	public void setDateOnset(String dateOnset) {
		this.dateOnset = dateOnset;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public String getReporterContact() {
		return reporterContact;
	}

	public void setReporterContact(String reporterContact) {
		this.reporterContact = reporterContact;
	}

	public String getInvestigatorLab() {
		return investigatorLab;
	}

	public void setInvestigatorLab(String investigatorLab) {
		this.investigatorLab = investigatorLab;
	}

	public String getInvestigatorName() {
		return investigatorName;
	}

	public void setInvestigatorName(String investigatorName) {
		this.investigatorName = investigatorName;
	}

	public String getInvestigatorContact() {
		return investigatorContact;
	}

	public void setInvestigatorContact(String investigatorContact) {
		this.investigatorContact = investigatorContact;
	}

	public String getFinalDiagnosis() {
		return finalDiagnosis;
	}

	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}

	public String getCRFID() {
		return CRFID;
	}

	public void setCRFID(String CRFID) {
		this.CRFID = CRFID;
	}
}
