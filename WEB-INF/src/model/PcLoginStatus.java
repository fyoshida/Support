package model;

import java.util.Date;

public class PcLoginStatus {
	private boolean isLogin = false;
	private String userName=null;

	public void login(String name) {
		isLogin = true;
		userName="name";
	}

	public void logout() {
		isLogin = false;
		userName=null;
	}

	public boolean isLogin() {
		return isLogin;
	}
	public String getUserName() {
		return userName;
	}
//	private void updateLoginStatus(){
//		if(Clock.isLoginTimeOver()) {
//			isLogin=false;
//			loginTime=null;
//		}
//	}
}
