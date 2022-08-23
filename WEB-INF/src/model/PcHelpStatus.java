package model;

import java.util.Date;

public class PcHelpStatus {
	private HelpStatus helpStatus = HelpStatus.None;
	private Date handTime = null;

	public void hand() {
		helpStatus=HelpStatus.Troubled;
		handTime=Clock.getCurrentTime();
	}

	public void supported() {
		helpStatus=HelpStatus.Supporting;
	}

	public void troubleComplete() {
		helpStatus=HelpStatus.None;
		handTime=null;
	}

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public Date getHandTime() {
		return handTime;
	}
}
}
