package com.idsr.mobile.models;

import java.util.ArrayList;

public class CaseData {
	private String patientAdmitted;
	// page 2
	private String sympFever;
	private String sympRash;
	private Boolean sympLymph;
	private Boolean sympCough;
	private Boolean sympKoplik;
	private Boolean sympRunnynose;
	private Boolean sympRedeye;
	private Boolean sympArthrisis;
	private String complications;
	private String otherSymptoms;
	private String diagnosis;
	// page 3
	private String MCVaccine;
	private String MCVmv;
	private String MCVmr;
	private String MCVmmr;
	private String MCVlastDoseDate;
	private String MCVvalidation;
	private String MCVCampaign;
	private ArrayList<String> noMCVreason;
	private String vitA;
	// page 4
	private String travelHistory;
	private String travelHistoryPlace;
	private String travelHistoryDate;
	private String travelDaysRashOnset;
	private String expContactMeasles;
	private String expContactRubella;
	private String expContactName;
	private String expContactPlace;
	private String expContactDate;
	private String expPlaceType;
	private String otherCommunityCases;
	// page 5
	private String labSpecimen;
	private String labDateCollected;
	private String labDateSent;
	private String labDateReceived;
	private String labMeaslesResult;
	private String labRubellaResult;
	private String labVirusResult;
	private String labPCRResult;
	// Page 6++
	private String finalClassification;
	private String sourceInfection;
	private String outcome;
	private String dateDied;
	private String finalDiagnosis;

	public CaseData() {
	}

	public void setPatientAdmitted(String patientAdmitted) {
		this.patientAdmitted = patientAdmitted;
	}

	public void setSympFever(String sympFever) {
		this.sympFever = sympFever;
	}

	public void setSympRash(String sympRash) {
		this.sympRash = sympRash;
	}

	public void setSympLymph(Boolean sympLymph) {
		this.sympLymph = sympLymph;
	}

	public void setSympCough(Boolean sympCough) {
		this.sympCough = sympCough;
	}

	public void setSympKoplik(Boolean sympKoplik) {
		this.sympKoplik = sympKoplik;
	}

	public void setSympRunnynose(Boolean sympRunnynose) {
		this.sympRunnynose = sympRunnynose;
	}

	public void setSympRedeye(Boolean sympRedeye) {
		this.sympRedeye = sympRedeye;
	}

	public void setSympArthrisis(Boolean sympArthrisis) {
		this.sympArthrisis = sympArthrisis;
	}

	public void setComplications(String complications) {
		this.complications = complications;
	}

	public void setOtherSymptoms(String otherSymptoms) {
		this.otherSymptoms = otherSymptoms;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public void setMCVaccine(String MCVaccine) {
		this.MCVaccine = MCVaccine;
	}

	public void setMCVmv(String MCVmv) {
		this.MCVmv = MCVmv;
	}

	public void setMCVmr(String MCVmr) {
		this.MCVmr = MCVmr;
	}

	public void setMCVmmr(String MCVmmr) {
		this.MCVmmr = MCVmmr;
	}

	public void setMCVlastDoseDate(String MCVlastDoseDate) {
		this.MCVlastDoseDate = MCVlastDoseDate;
	}

	public void setMCVvalidation(String MCVvalidation) {
		this.MCVvalidation = MCVvalidation;
	}

	public void setMCVCampaign(String MCVCampaign) {
		this.MCVCampaign = MCVCampaign;
	}

	public void setNoMCVreason(ArrayList<String> noMCVreason) {
		this.noMCVreason = noMCVreason;
	}

	public void setVitA(String vitA) {
		this.vitA = vitA;
	}

	public void setTravelHistory(String travelHistory) {
		this.travelHistory = travelHistory;
	}

	public void setTravelHistoryPlace(String travelHistoryPlace) {
		this.travelHistoryPlace = travelHistoryPlace;
	}

	public void setTravelHistoryDate(String travelHistoryDate) {
		this.travelHistoryDate = travelHistoryDate;
	}

	public void setTravelDaysRashOnset(String travelDaysRashOnset) {
		this.travelDaysRashOnset = travelDaysRashOnset;
	}

	public void setExpContactMeasles(String expContactMeasles) {
		this.expContactMeasles = expContactMeasles;
	}

	public void setExpContactRubella(String expContactRubella) {
		this.expContactRubella = expContactRubella;
	}

	public void setExpContactName(String expContactName) {
		this.expContactName = expContactName;
	}

	public void setExpContactPlace(String expContactPlace) {
		this.expContactPlace = expContactPlace;
	}

	public void setExpContactDate(String expContactDate) {
		this.expContactDate = expContactDate;
	}

	public void setExpPlaceType(String expPlaceType) {
		this.expPlaceType = expPlaceType;
	}

	public void setOtherCommunityCases(String otherCommunityCases) {
		this.otherCommunityCases = otherCommunityCases;
	}

	public void setLabSpecimen(String labSpecimen) {
		this.labSpecimen = labSpecimen;
	}

	public void setLabDateCollected(String labDateCollected) {
		this.labDateCollected = labDateCollected;
	}

	public void setLabDateSent(String labDateSent) {
		this.labDateSent = labDateSent;
	}

	public void setLabDateReceived(String labDateReceived) {
		this.labDateReceived = labDateReceived;
	}

	public void setLabMeaslesResult(String labMeaslesResult) {
		this.labMeaslesResult = labMeaslesResult;
	}

	public void setLabRubellaResult(String labRubellaResult) {
		this.labRubellaResult = labRubellaResult;
	}

	public void setLabVirusResult(String labVirusResult) {
		this.labVirusResult = labVirusResult;
	}

	public void setLabPCRResult(String labPCRResult) {
		this.labPCRResult = labPCRResult;
	}

	public void setFinalClassification(String finalClassification) {
		this.finalClassification = finalClassification;
	}

	public void setSourceInfection(String sourceInfection) {
		this.sourceInfection = sourceInfection;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public void setDateDied(String dateDied) {
		this.dateDied = dateDied;
	}

	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}

	/* unusable code
		private String fieldName;
		private String value;
		public CaseData(String fieldName, String value) {
			this.fieldName = fieldName;
			this.value = value;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	 */
}
