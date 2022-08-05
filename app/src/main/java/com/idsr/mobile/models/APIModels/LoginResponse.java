package com.idsr.mobile.models.APIModels;

import com.idsr.mobile.models.User;

public class LoginResponse {
	private User user;

	public LoginResponse(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
