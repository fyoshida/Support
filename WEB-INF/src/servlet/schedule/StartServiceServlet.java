package servlet.schedule;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.StudentManager;


@jakarta.servlet.annotation.WebListener
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