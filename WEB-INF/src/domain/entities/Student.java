package domain.entities;

import static org.apache.commons.lang3.Validate.*;

import java.time.Duration;
import java.time.LocalDateTime;

import domain.valueobjects.HelpStatus;

public class Student {

	private Pc pc = null;

	private HelpStatus helpStatus = HelpStatus.None;
	private LocalDateTime handUpTime = null;
	private int priority=0;

	//--------コンストラクタ--------------
	public Student(Pc pc) {
		notNull(pc);
		this.pc = pc;
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

	public boolean isHandup() {
		return helpStatus.equals(HelpStatus.Troubled);
	}
	
	public long getWaitingTimeBySecond(LocalDateTime dateTime) {
		if(handUpTime==null) {
			return 0;
		}
		return Duration.between(handUpTime,dateTime).toSeconds();
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	//--------hand処理--------------
	public void handUp() {
		helpStatus = HelpStatus.Troubled;
		handUpTime = LocalDateTime.now();
	}

	public void supporting() {
		helpStatus = HelpStatus.Supporting;
		handUpTime = null;
	}

	public void handDown() {
		helpStatus = HelpStatus.None;
		handUpTime = null;
	}

	public boolean equals(Object other) {
		if(!( other instanceof Student)){
			return false;
		}
		Student student = (Student)other;
		return pc.equals(student.getPc());
	}
}
