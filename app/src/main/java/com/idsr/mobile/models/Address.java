package com.idsr.mobile.models;

public class Address {
	private String addressID;
	private String houseStreet;
	private String brgy;
	private String city;

	public Address() {
	}

	public Address(String houseStreet, String brgy, String city) {
		this.houseStreet = houseStreet;
		this.brgy = brgy;
		this.city = city;
	}

	public Address(String addressID, String houseStreet, String brgy, String city) {
		this.addressID = addressID;
		this.houseStreet = houseStreet;
		this.brgy = brgy;
		this.city = city;
	}

	public String getAddressID() {
		return addressID;
	}

	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}

	public String getHouseStreet() {
		return houseStreet;
	}

	public void setHouseStreet(String houseStreet) {
		this.houseStreet = houseStreet;
	}

	public String getBrgy() {
		return brgy;
	}

	public void setBrgy(String brgy) {
		this.brgy = brgy;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
