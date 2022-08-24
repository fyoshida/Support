package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class PcClient extends Pc{

	private String userName = null;

	private HelpStatus helpStatus = HelpStatus.None;
	private LocalDateTime handTime = null;

	private WaitingManager waitingManager = null;

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
		handTime = LocalDateTime.now();
		waitingManager.regist(pcId);
	}

	public void supported() {
		helpStatus = HelpStatus.Supporting;
		waitingManager.unregist(pcId);
	}

	public void handDown() {
		helpStatus = HelpStatus.None;
		handTime = null;
		waitingManager.unregist(pcId);
	}

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public LocalDateTime getHandTime() {
		return handTime;
	}

	//--------順序処理--------------
	public void setPriorityManager(WaitingManager priorityManager) {
		this.waitingManager = priorityManager;
	}

	public int getPriority() {
		return waitingManager.getPriority(pcId);
	}

	public Duration getWaitingTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		return Duration.between(currentTime, handTime);
	}

}
