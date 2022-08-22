package model;

import java.util.Date;

public class Pc {

	protected final String pcId; //icsXXX
	protected final String ipAddress; //ドメインは133.44.118.158-228の間

	private Boolean isLogin = false;
	private Date loginTime=null;

	private HelpStatus helpStatus = null; // 手を挙げていない: None
	private Date handTime = null; //最終挙手時間

	//--------コンストラクタ--------------
	public Pc(String pcId,String ipAddress){
		this.pcId=pcId;
		this.ipAddress=ipAddress;
	}

	//--------アクセッサ--------------

	public String getPcId() {
		return pcId;
	}

	public String getIpAdress() {
		return ipAddress;
	}

	//--------比較処理--------------
	public boolean equals(Pc pc) {
		if(pcId.equals(pc.pcId)) {
			return true;
		}else {
			return false;
		}
	}

	//--------Login処理---------------
	public void login() {
		isLogin = true;
		loginTime = Clock.getCurrentTime();
	}

	private void updateLoginStatus(){
		if(Clock.isLoginTimeOver()) {
			isLogin=false;
			loginTime=null;
		}
	}

	public boolean isLogin() {
		return isLogin;
	}

	//--------挙手処理---------------
	public void hand() {
		helpStatus=HelpStatus.Troubled;
		handTime=Clock.getCurrentTime();
		updateLoginStatus();
	}

	public void supported() {
		helpStatus=HelpStatus.Supporting;
		updateLoginStatus();
	}

	public void troubleComplete() {
		helpStatus=HelpStatus.None;
		handTime=null;
		updateLoginStatus();
	}

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public Date getHandTime() {
		return handTime;
	}
}
