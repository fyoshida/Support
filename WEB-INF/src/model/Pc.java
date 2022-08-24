package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Pc {

	protected PcId pcId = null;;
	protected boolean isStudent = false;

	private String userName = null;

	private HelpStatus helpStatus = HelpStatus.None;
	private LocalDateTime handUpTime = null;

	private WaitingManager waitingManager = null;

	//--------アクセッサ--------------
	public void setPcId(PcId pcId) {
		this.pcId = pcId;
	}

	public PcId getPcId() {
		return pcId;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public void setHelpStatus(HelpStatus helpStatus) {
		this.helpStatus = helpStatus;
	}

	public void setHandUpTime(LocalDateTime handUpTime) {
		this.handUpTime = handUpTime;
	}

	public void setPriorityManager(WaitingManager priorityManager) {
		this.waitingManager = priorityManager;
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
			return pcId.getHostName();
		}
	}

	//--------hand処理--------------
	public void handUp() {
		helpStatus = HelpStatus.Troubled;
		handUpTime = LocalDateTime.now();
		waitingManager.regist(pcId);
	}

	public void supported() {
		helpStatus = HelpStatus.Supporting;
		waitingManager.unregist(pcId);
	}

	public void handDown() {
		helpStatus = HelpStatus.None;
		handUpTime = null;
		waitingManager.unregist(pcId);
	}

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public LocalDateTime getHandTime() {
		return handUpTime;
	}

	//--------順序処理--------------
	public int getPriority() {
		return waitingManager.getPriority(pcId);
	}

	public Duration getWaitingTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		return Duration.between(currentTime, handUpTime);
	}

}
