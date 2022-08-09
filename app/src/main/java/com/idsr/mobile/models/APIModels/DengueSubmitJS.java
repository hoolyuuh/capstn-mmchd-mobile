package com.idsr.mobile.models.APIModels;

import com.idsr.mobile.models.CaseForm;

public class DengueSubmitJS {
	private String CRFID;
	private CaseForm formData;

	public DengueSubmitJS(String CRFID, CaseForm formData) {
		this.CRFID = CRFID;
		this.formData = formData;
	}

	public String getCRFID() {
		return CRFID;
	}

	public void setCRFID(String CRFID) {
		this.CRFID = CRFID;
	}

	public CaseForm getFormData() {
		return formData;
	}

	public void setFormData(CaseForm formData) {
		this.formData = formData;
	}
}
