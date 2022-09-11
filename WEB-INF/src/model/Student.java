package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Student {

	private String ipAddress;
	private String hostName;
	private boolean isStudent = false;

	private String userName = null;
	private HelpStatus helpStatus = HelpStatus.None;
	private LocalDateTime handUpTime = null;
	private int waitingOrder = 999;

	//--------コンストラクタ--------------
	public Student(String ipAddress, String hostName, boolean isStudent) {
		this.ipAddress = ipAddress;
		this.hostName = hostName;
		this.isStudent = isStudent;
	}

//	public Student(String ipAddress, String hostName, boolean isStudent,
//			String userName, HelpStatus helpStatus, LocalDateTime handUpTime, int waitingOrder) {
//		this.ipAddress = ipAddress;
//		this.hostName = hostName;
//		this.isStudent = isStudent;
//		this.userName = userName;
//		this.helpStatus = helpStatus;
//		this.handUpTime = handUpTime;
//		this.waitingOrder = waitingOrder;
//	}

	//--------アクセッサ--------------
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostName() {
		return hostName;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
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

	public void setWaitingOrder(int waitingOrder) {
		this.waitingOrder = waitingOrder;
	}

	public int getWaitingOrder() {
		return waitingOrder;
	}

		//--------hand処理--------------
		public void handUp() {
			helpStatus = HelpStatus.Troubled;
			handUpTime = LocalDateTime.now();
			waitingManager.regist(this);
		}
	
		public void supported() {
			helpStatus = HelpStatus.Supporting;
			handUpTime = null;
			waitingManager.unregist(this);
		}
	
		public void handDown() {
			helpStatus = HelpStatus.None;
			handUpTime = null;
			waitingManager.unregist(this);
		}
	//
	//	//--------待ち行列処理--------------
	//	public int getPriority() {
	//		return waitingManager.getPriority(this);
	//	}
	//
	//	public Duration getWaitingTime(LocalDateTime currentTime) {
	//		if (handUpTime == null) {
	//			return null;
	//		} else {
	//			return Duration.between(currentTime, handUpTime);
	//		}
	//	}

	//--------比較用基本関数--------------
	@Override
	public int hashCode() {
		return Objects.hash(ipAddress);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Student)) {
			return false;
		}

		Student student = (Student) object;
		if (student.getIpAddress().equals(this.getIpAddress())) {
			return true;
		}
		return false;

	}

}
