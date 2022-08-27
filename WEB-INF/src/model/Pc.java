package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Pc extends PcBean {

	private String userName = null;

	private HelpStatus helpStatus = HelpStatus.None;
	private LocalDateTime handUpTime = null;

	private WaitingManager waitingManager = null;

	//--------コンストラクタ--------------
	public Pc(IpAddress ipAddress, WaitingManager wm) {
		this.ipAddress = ipAddress;
		this.waitingManager = wm;
	}

	//--------アクセッサ--------------
	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public void setHandUpTime(LocalDateTime handUpTime) {
		this.handUpTime = handUpTime;
	}

	public LocalDateTime getHandUpTime() {
		return handUpTime;
	}

	public void setWaitingManager(WaitingManager waitingManager) {
		this.waitingManager = waitingManager;
	}

	public String getUserName() {
		if (userName != null) {
			return userName;
		} else {
			return hostName;
		}
	}

	//--------login処理--------------
	public void login(String name) {
		userName = name;
	}

	public void logout() {
		userName = null;
	}

	public boolean isLogin() {
		return (userName != null);
	}

	//--------hand処理--------------
	public void handUp() {
		helpStatus = HelpStatus.Troubled;
		handUpTime = LocalDateTime.now();
		waitingManager.regist(ipAddress);
	}

	public void supported() {
		helpStatus = HelpStatus.Supporting;
		waitingManager.unregist(ipAddress);
	}

	public void handDown() {
		helpStatus = HelpStatus.None;
		handUpTime = null;
		waitingManager.unregist(ipAddress);
	}

	//--------待ち行列処理--------------
	public int getPriority() {
		return waitingManager.getPriority(ipAddress);
	}

	public Duration getWaitingTime() {
		if (handUpTime == null) {
			return null;
		} else {
			LocalDateTime currentTime = LocalDateTime.now();
			return Duration.between(currentTime, handUpTime);
		}
	}

}
