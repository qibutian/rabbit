package com.means.rabbit.bean;

public class User {

	static User instance;

	public static User getInstance() {
		if (instance == null) {
			instance = new User();
		}

		return instance;
	}

	public boolean isLogin;

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
