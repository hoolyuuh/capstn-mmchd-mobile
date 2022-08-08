package com.idsr.mobile.models.APIModels;

import com.idsr.mobile.models.Immunization;
import com.idsr.mobile.models.Patient;
import com.idsr.mobile.models.RiskFactors;

public class TCLJS {
	private FormData formData;
	private String TCLID;
	private Immunization immunisationData;

	public TCLJS() {
	}

	public FormData getFormData() {
		return formData;
	}

	public void setFormData(FormData formData) {
		this.formData = formData;
	}

	public String getTCLID() {
		return TCLID;
	}

	public void setTCLID(String TCLID) {
		this.TCLID = TCLID;
	}

	public Immunization getImmunisationData() {
		return immunisationData;
	}

	public void setImmunisationData(Immunization immunisationData) {
		this.immunisationData = immunisationData;
	}

	public static class FormData{
		private Patient patient;

		public FormData(Patient patient) {
			this.patient = patient;
		}

		public Patient getPatient() {
			return patient;
		}

		public void setPatient(Patient patient) {
			this.patient = patient;
		}
	}
}
