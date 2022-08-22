package model;

import java.sql.Timestamp;
import java.util.Date;

public class Clock {
	private final Date startTime;

	public Clock(){
		long millis = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(millis);
		startTime=new Date(timestamp.getTime());
	}

	public static Date getCurrentTime() {
		long millis = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(millis);
		return new Date(timestamp.getTime());
	}

	public static boolean isLoginTimeOver() {

		return false;
	}
}
