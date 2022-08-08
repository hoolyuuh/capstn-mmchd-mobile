package com.idsr.mobile.models;

public class UserSettings {
	private String userID;
	private int pushDataAccept;

	public UserSettings() {
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getPushDataAccept() {
		return pushDataAccept;
	}

	public void setPushDataAccept(int pushDataAccept) {
		this.pushDataAccept = pushDataAccept;
	}
}
