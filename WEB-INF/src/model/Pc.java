package model;

import java.util.Date;

public class Pc {

	private final String pcId; //icsXXX
	private final String ipAddress; //ドメインは133.44.118.158-228の間
	private PcLoginStatus loginStatus;
	private PcHelpStatus helpStatus;


	//--------コンストラクタ--------------
	public Pc(String pcId,String ipAddress){
		this.pcId=pcId;
		this.ipAddress=ipAddress;
		loginStatus=new PcLoginStatus();
		helpStatus=new PcHelpStatus();
	}

	//--------アクセッサ--------------

	public String getPcId() {
		return pcId;
	}

	public String getIpAdress() {
		return ipAddress;
	}

	public String getUserName() {
		String userName =loginStatus.getUserName();
		if(userName != null) {
			return userName;
		}else {
			return pcId;
		}
	}

	public HelpStatus getHelpStatus() {
		return helpStatus.getHelpStatus();
	}

	public Date getHandTime() {
		return helpStatus.getHandTime();
	}

	//--------比較処理--------------
	public boolean equals(Pc pc) {
		if(pcId.equals(pc.pcId)) {
			return true;
		}else {
			return false;
		}
	}
}
