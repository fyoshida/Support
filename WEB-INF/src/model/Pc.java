package model;

import static org.apache.commons.lang3.Validate.*;

import java.time.Duration;
import java.time.LocalDateTime;

public class Pc extends PcBean {

	private String userName = null;

	private HelpStatus helpStatus = HelpStatus.None;
	private LocalDateTime handUpTime = null;
	private WaitingManager waitingManager = null;

	//--------コンストラクタ--------------
	public Pc(IpAddress ipAddress, WaitingManager waitingManager) {
		notNull(ipAddress);
		notNull(waitingManager);
		this.ipAddress = ipAddress;
		this.waitingManager = waitingManager;
	}

	//--------equals--------------
	public boolean equals(Pc pc) {
		return ipAddress.equals(pc.getIpAddress());
	}

	//--------アクセッサ--------------
	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public LocalDateTime getHandUpTime() {
		return handUpTime;
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
