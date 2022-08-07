package com.idsr.mobile.models.APIModels;

import com.idsr.mobile.models.User;

import java.util.List;

public class UserListResponse {
	private List<User> users;

	public UserListResponse(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
