package com.idsr.mobile.models;

import java.util.Date;

public class Event {
	private String eventID, userID, addressID, eventStatus, source, reportSource, eventDetails, remarks;
	private String numCases, numDeaths;
	private String dateCaptured, timeCaptured, dateReported;
	private String locHouseStreet, locCity, locBrgy;

	public Event() {
		this.eventStatus = new String("For Validation");
		this.eventID = "";
		this.addressID = "";

	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getAddressID() {
		return addressID;
	}

	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getReportSource() {
		return reportSource;
	}

	public void setReportSource(String reportSource) {
		this.reportSource = reportSource;
	}

	public String getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getNumCases() {
		return numCases;
	}

	public void setNumCases(String numCases) {
		this.numCases = numCases;
	}

	public String getNumDeaths() {
		return numDeaths;
	}

	public void setNumDeaths(String numDeaths) {
		this.numDeaths = numDeaths;
	}

	public String getDateCaptured() {
		return dateCaptured;
	}

	public void setDateCaptured(String dateCaptured) {
		this.dateCaptured = dateCaptured;
	}

	public String getTimeCaptured() {
		return timeCaptured;
	}

	public void setTimeCaptured(String timeCaptured) {
		this.timeCaptured = timeCaptured;
	}

	public String getDateReported() {
		return dateReported;
	}

	public void setDateReported(String dateReported) {
		this.dateReported = dateReported;
	}

	public String getLocHouseStreet() {
		return locHouseStreet;
	}

	public void setLocHouseStreet(String locHouseStreet) {
		this.locHouseStreet = locHouseStreet;
	}

	public String getLocCity() {
		return locCity;
	}

	public void setLocCity(String locCity) {
		this.locCity = locCity;
	}

	public String getLocBrgy() {
		return locBrgy;
	}

	public void setLocBrgy(String locBrgy) {
		this.locBrgy = locBrgy;
	}

	public int getSourceIndex() {
		if(source.equals("Print")) return 0;
		else if (source.equals("Internet")) return 1;
		else if (source.equals("Television")) return 2;
		else if (source.equals("Radio")) return 3;
		else if (source.equals("DOH")) return 4;
		else return 5;
	}
}
