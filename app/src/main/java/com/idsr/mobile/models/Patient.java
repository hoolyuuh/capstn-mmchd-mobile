package com.idsr.mobile.models;

import java.util.Date;

public class Patient {
	private String patientID;
	private String epiID;
	private String lastName;
	private String firstName;
	private String midName;
	private String caddressID;
	private String paddressID;
	private String sex;
	private Date birthdate;
	private int agoNo;
	private String ageType;
	private String admitStatus;
	private String civilStatus;
	private String occupation;
	private String occuLoc;
	private String occuAddrID;
	private String guardianName;
	private String guardianContact;
	private String indGroup;
	private String pregWeeks;
	private String HCPN;
	private String ILHZ;

	public Patient() {
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getEpiID() {
		return epiID;
	}

	public void setEpiID(String epiID) {
		this.epiID = epiID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getCaddressID() {
		return caddressID;
	}

	public void setCaddressID(String caddressID) {
		this.caddressID = caddressID;
	}

	public String getPaddressID() {
		return paddressID;
	}

	public void setPaddressID(String paddressID) {
		this.paddressID = paddressID;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public int getAgoNo() {
		return agoNo;
	}

	public void setAgoNo(int agoNo) {
		this.agoNo = agoNo;
	}

	public String getAgeType() {
		return ageType;
	}

	public void setAgeType(String ageType) {
		this.ageType = ageType;
	}

	public String getAdmitStatus() {
		return admitStatus;
	}

	public void setAdmitStatus(String admitStatus) {
		this.admitStatus = admitStatus;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOccuLoc() {
		return occuLoc;
	}

	public void setOccuLoc(String occuLoc) {
		this.occuLoc = occuLoc;
	}

	public String getOccuAddrID() {
		return occuAddrID;
	}

	public void setOccuAddrID(String occuAddrID) {
		this.occuAddrID = occuAddrID;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianContact() {
		return guardianContact;
	}

	public void setGuardianContact(String guardianContact) {
		this.guardianContact = guardianContact;
	}

	public String getIndGroup() {
		return indGroup;
	}

	public void setIndGroup(String indGroup) {
		this.indGroup = indGroup;
	}

	public String getPregWeeks() {
		return pregWeeks;
	}

	public void setPregWeeks(String pregWeeks) {
		this.pregWeeks = pregWeeks;
	}

	public String getHCPN() {
		return HCPN;
	}

	public void setHCPN(String HCPN) {
		this.HCPN = HCPN;
	}

	public String getILHZ() {
		return ILHZ;
	}

	public void setILHZ(String ILHZ) {
		this.ILHZ = ILHZ;
	}
}
