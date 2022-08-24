package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Pc {

	protected IpAddress ipAddress=null;
	protected String hostName="";
	protected boolean isStudent = false;

	private String userName = null;

	private HelpStatus helpStatus = HelpStatus.None;
	private LocalDateTime handUpTime = null;

	private WaitingManager waitingManager = null;

	//--------アクセッサ--------------
	public void setIpAddress(IpAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public IpAddress getIpAddress() {
		return ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
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

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public void setHandUpTime(LocalDateTime handUpTime) {
		this.handUpTime = handUpTime;
	}

	public LocalDateTime getHandUpTime() {
		return handUpTime;
	}

	public void setPriorityManager(WaitingManager priorityManager) {
		this.waitingManager = priorityManager;
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
		userName = "name";
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
		LocalDateTime currentTime = LocalDateTime.now();
		return Duration.between(currentTime, handUpTime);
	}

}
