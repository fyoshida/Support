package model;

import static org.apache.commons.lang3.Validate.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Student {

	private Pc pc = null;

	private String userName = null;

	private HelpStatus helpStatus = HelpStatus.None;
	
	private LocalDateTime handUpTime = null;

	private WaitingManager<Student> waitingManager = null;

	//--------コンストラクタ--------------
	public Student(Pc pc, WaitingManager<Student> waitingManager) {
		notNull(pc);
		this.pc = pc;
		notNull(waitingManager);
		this.waitingManager = waitingManager;
	}

	//--------アクセッサ--------------
	public Pc getPc() {
		return pc;
	}

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public LocalDateTime getHandUpTime() {
		return handUpTime;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isLogin() {
		return (userName != null);
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

	//--------待ち行列処理--------------
	public int getPriority() {
		return waitingManager.getPriority(this);
	}

	public Duration getWaitingTime(LocalDateTime currentTime) {
		if (handUpTime == null) {
			return null;
		} else {
			return Duration.between(currentTime, handUpTime);
		}
	}
	
	//--------比較用基本関数--------------
	@Override
	public int hashCode() {
		return Objects.hash(pc);
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
		if (student.getPc().getIpAddress().equals(this.getPc().getIpAddress())) {
			return true;
		}
		return false;

	}

}
