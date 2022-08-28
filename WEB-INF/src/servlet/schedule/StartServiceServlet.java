package servlet.schedule;

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

import model.HelpStatus;
import model.Student;
import model.StudentManager;


@WebListener
public class StartServiceServlet implements ServletContextListener {
	private static StudentManager pcListManager;

	private ScheduleManager schedule=null;

	//-----------アクセッサ-----------------------------------------------------------------
	public static StudentManager getPcListManager() {
		return pcListManager;
	}

	//--------サーバの初回起動時に実行される関数-------------------------------------------
	public void contextInitialized(ServletContextEvent arg0) {

		schedule = ScheduleManager.getInstance();
		schedule.start();

		System.out.println("サーバを起動します");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// schedule.stop();
		System.out.println("サーバがダウンされました");
	}
}