package model;

import java.util.Date;

import servlet.StartServiceServlet;

public class Pc {

	// PC固有の属性
	protected String pcId = null; //icsXXX
	protected String ipAdress = null; //ドメインは133.44.118.158-228の間
	protected PcType type=null;

//	protected Boolean isStudent = null; // true:学生が利用するPC, false:TA,教員が利用するPC

	// ユーザの属性
	protected Boolean isLogin = null; // true:ログイン中, false:未ログイン
	protected Date lastHandTime = null; //最終挙手時間

	// 挙手の順番（サポート優先順位）
	protected int handPriority = -1;

	// サポート状況
	protected HelpStatus helpStatus = null; // 手を挙げていない: None
	//	手を挙げている: Troubled
	// TA教員対応中: Supporting

	protected Date lastRequestTime = null; //最終リクエスト時間

	//--------アクセッサ--------------

	public Date getLastRequestTime() {
		return lastRequestTime;
	}

	public void setLastRequestTime(Date lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
	}

	public Date getLastHandTime() {
		return lastHandTime;
	}

	public void setLastHandTime(Date lastHandTime) {
		this.lastHandTime = lastHandTime;
	}

	public void updateLastRequestTime() {
		// TODO 自動生成されたメソッド・スタブ

	}

	void updateHelpStatusByStudent() {
		//現在のヘルプ状態から遷移する None->Troubled, Troubled,Supporting->None
		switch (helpStatus){
			case None:
				helpStatus = HelpStatus.Troubled;
				break;
			case Troubled:
				helpStatus = HelpStatus.None;
				break;
			case Supporting:
				break;
		}
	}

	void updateHelpStatusBySupporter() {
		//現在のヘルプ状態から状態を遷移する "None"状態は遷移なし
		switch(helpStatus) {
		case None:
			break;
		case Troubled:
			helpStatus = HelpStatus.Supporting;
			break;
		case Supporting:
			helpStatus = HelpStatus.None;
			break;
		}

	}

	public void raiseHand() {
		updateHelpStatusByStudent();
		updateLastHandTime();
	}

	public void support() {
		updateHelpStatusBySupporter();
		updateLasHandTime();
	}

	public void login() {
		setLogin(true);
		updateRequestTime();
	}

	//--------アクセッサ--------------

	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public Boolean getIsStudent() {
		return isStudent;
	}
	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}
	public void setHelpStatus(HelpStatus helpStatus) {
		this.helpStatus = helpStatus;
	}

	public int getHandPriority() {
		return handPriority;
	}
	public void setHandPriority(int handPriority) {
		this.handPriority = handPriority;
	}

	public boolean isSamePcId(String pcId) {
		return this.pcId.equals(pcId);
	}
}

}
