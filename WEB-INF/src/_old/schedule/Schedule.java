package _old.schedule;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

class Schedule extends TimerTask {

    private static Schedule instance = new Schedule();
    private Schedule() {
    }

    @Override
	public void run() {
		// 実行したい処理

    }

    public static Schedule getInstance() {
        return instance;
    }
}