package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class Pc {

	protected PcId pcId = null;;
	protected boolean isStudent = false;

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


}
