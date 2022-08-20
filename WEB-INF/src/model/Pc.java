package model;

import java.util.Date;

import servlet.StartServiceServlet;

public class Pc extends PcJson{
	protected Date lastRequestTime = null; //最終リクエスト時間
	protected Date lastHandTime = null; //最終挙手時間

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

	public void updateHelpStatusByRaiseHand() {
		//現在のヘルプ状態から遷移する None->Troubled, Troubled,Supporting->None
		switch (helpStatus){
			case None:
				helpStatus = HelpStatus.Troubled;
				StartServiceServlet.setHandTime("ics"+myPcId, false);
				break;
			case Troubled:
				helpStatus = HelpStatus.None;
				break;
			case Supporting:
				break;
		}

//		StartServlet.setHelpStatus("ics"+myPcId, HelpStatus.None);
//		StartServlet.setHandTime("ics"+myPcId, true);

	}

	public void updateHelpStatusBySupport() {
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

//		StartServlet.setHelpStatus("ics"+myPcId, HelpStatus.None);
//		StartServlet.setHandTime("ics"+myPcId, true);

	}



}
