package servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import beans.HelpStatus;
import beans.Pc;
import helper.PcListFileReader;
import helper.PcListManager;
import helper.ScheduleManager;

@WebListener
public class StartServlet implements ServletContextListener {
	private static final String PCLIST_FILENAME="/WEB-INF/data/pcIdTable.csv";

	private static PcListManager pcListManager;

	//--------サーバの初回起動時に実行される関数-------------------------------------------
	public void contextInitialized(ServletContextEvent arg0) {

		//ファイルの読み込み
		PcListFileReader reader = new PcListFileReader();
		List<Pc> pcList =reader.getPcListFromFile(PCLIST_FILENAME);
		PcListManager pcManager=new PcListManager(pcList);

		ScheduleManager schedule = ScheduleManager.getInstance();
		schedule.start();

		System.out.println("サーバを起動します");
	}

	public void PcListManager(ServletContextEvent arg0) {
		System.out.println("サーバがダウンされました");
	}



	//-----------アクセッサ-----------------------------------------------------------------
	public static PcListManager getPcListManager() {
		return pcListManager;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}
}