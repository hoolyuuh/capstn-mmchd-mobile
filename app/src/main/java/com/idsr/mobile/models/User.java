package com.idsr.mobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class User implements Parcelable {
	private String userID;
	private String userType;
	private String userName;

	public User(String userID, String userType, String userName) {
		this.userID = userID;
		this.userType = userType;
		this.userName = userName;
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userID);
		dest.writeString(userType);
		dest.writeString(userName);
	}

	public User(Parcel in){
		this.userID = in.readString();
		this.userType = in.readString();
		this.userName = in.readString();
	}
}
