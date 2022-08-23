package model;

import java.util.Date;

public class Pc {

	private final String pcId; //icsXXX
	private final String ipAddress; //ドメインは133.44.118.158-228の間

	private String userName = null;

	private HelpStatus helpStatus = HelpStatus.None;
	private Date handTime = null;

	//--------コンストラクタ--------------
	public Pc(String pcId, String ipAddress) {
		this.pcId = pcId;
		this.ipAddress = ipAddress;
	}

	//--------比較処理--------------
	public boolean equals(Pc pc) {
		if (pcId.equals(pc.pcId)) {
			return true;
		} else {
			return false;
		}
	}

	//--------処理--------------
	public String getPcId() {
		return pcId;
	}

	public String getIpAdress() {
		return ipAddress;
	}

	//--------login処理--------------
	public void login(String name) {
		userName = "name";
	}

	public void logout() {
		userName = null;
	}

	public boolean isLogin() {
		return (userName != null);
	}

	public String getUserName() {
		if (userName != null) {
			return userName;
		} else {
			return pcId;
		}
	}

	//--------hand処理--------------
	public void hand() {
		helpStatus = HelpStatus.Troubled;
		handTime = Clock.getCurrentTime();
		PriorityManager.regist(pcId);
	}

	public void supported() {
		helpStatus = HelpStatus.Supporting;
		PriorityManager.unregist(pcId);
	}

	public void troubleClear() {
		helpStatus = HelpStatus.None;
		handTime = null;
		PriorityManager.unregist(pcId);
	}

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public Date getHandTime() {
		return handTime;
	}

	public int getPriority() {
		return PriorityManager.getPriority(pcId);
	}
}
