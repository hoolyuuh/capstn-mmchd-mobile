package com.idsr.mobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class User implements Parcelable {
	private String userID;
	private String userType;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String addressID;
	private String lastName;
	private String firstName;
	private String midName;
	private String userContactNo1;
	private String userContactNo2;
	private String druName;

	public User(String userID, String userType, String userName) {
		this.userID = userID;
		this.userType = userType;
		this.userName = userName;
	}

	public User(String userID, String userType, String userName, String userPassword,
				String userEmail, String addressID, String lastName, String firstName,
				String midName, String userContactNo1, String userContactNo2, String druName) {
		this.userID = userID;
		this.userType = userType;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.addressID = addressID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.midName = midName;
		this.userContactNo1 = userContactNo1;
		this.userContactNo2 = userContactNo2;
		this.druName = druName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// Parcelable Methods
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
		public User createFromParcel(Parcel in){
			return  new User(in);
		}

		public User[] newArray(int size){
			return new User[size];
		}
	};

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAddressID() {
		return addressID;
	}

	public void setAddressID(String addressID) {
		this.addressID = addressID;
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

	public String getUserContactNo1() {
		return userContactNo1;
	}

	public void setUserContactNo1(String userContactNo1) {
		this.userContactNo1 = userContactNo1;
	}

	public String getUserContactNo2() {
		return userContactNo2;
	}

	public void setUserContactNo2(String userContactNo2) {
		this.userContactNo2 = userContactNo2;
	}

	public String getDruName() {
		return druName;
	}

	public void setDruName(String druName) {
		this.druName = druName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userID);
		dest.writeString(userType);
		dest.writeString(userName);
		dest.writeString(userPassword);
		dest.writeString(userEmail);
		dest.writeString(addressID);
		dest.writeString(lastName);
		dest.writeString(firstName);
		dest.writeString(midName);
		dest.writeString(userContactNo1);
		dest.writeString(userContactNo2);
		dest.writeString(druName);
	}

	public User(Parcel in){
		this.userID = in.readString();
		this.userType = in.readString();
		this.userName = in.readString();
		this.userPassword = in.readString();
		this.userEmail = in.readString();
		this.addressID = in.readString();
		this.lastName = in.readString();
		this.firstName = in.readString();
		this.midName = in.readString();
		this.userContactNo1 = in.readString();
		this.userContactNo2 = in.readString();
		this.druName = in.readString();
	}
}
