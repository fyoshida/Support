package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HelpStatusConverter;
import helper.JsonConverter;
import helper.PcJson;
import helper.PcJsonHelper;
import model.HelpStatus;
import model.Student;
import model.StudentManager;

//@WebServlet(urlPatterns = { "/v1/student/*" })
//call-teacher/XXXの応答関数
public class StudentServlet extends HttpServlet {

	// PUT
	// 手を上げる
	// 手を下ろす
	// サポート
	// ログイン
	// ログアウト

	// GET
	// 全PC
	// 特定のPC

	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentManager studentManager = (StudentManager) sc.getAttribute("StudentManager");

		// ホスト名を取得
		String hostName = req.getParameter("HostName");

		// 学生を取得
		Student student = studentManager.findStudentByHostName(hostName);
		if (student == null) {
			req.getRequestDispatcher("/error.html").forward(req, resp);
			return;
		}

		// 変更する属性を取得
		String helpStatusString = req.getParameter("HelpStatus");
		String userName = req.getParameter("UserName");

		// 属性を更新
		if (helpStatusString != null) {
			HelpStatus helpStatus = HelpStatusConverter.getHelpStatus(helpStatusString);
			switch (helpStatus) {
			case None:
				student.handDown();
				break;
			case Troubled:
				student.handUp();
				break;
			case Supporting:
				student.supported();
				break;
			default:
			}
		}
		if (userName != null) {
			student.setUserName(userName);
		}

		//pcManagerを保存.
		sc.setAttribute("StudentManager", studentManager);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentManager studentManager = (StudentManager) sc.getAttribute("StudentManager");

		// ホスト名を取得
		String hostName = req.getParameter("HostName");

		String jsonText = "";
		if (hostName == null) {

			// 全学生を取得
			List<Student> studentList = studentManager.getStudentList();

			// Student --> PcJson
			List<PcJson> pcJsonList = PcJsonHelper.getPcJson(studentList);

			// JSON形式で出力
			jsonText = JsonConverter.getJsonText(pcJsonList);

		} else {
			// 学生を取得
			Student student = studentManager.findStudentByHostName(hostName);
			if (student == null) {
				req.getRequestDispatcher("/error.html").forward(req, resp);
				return;
			}

			// Student --> PcJson
			PcJson pcJson = PcJsonHelper.getPcJson(student);

			// JSON形式で出力
			jsonText = JsonConverter.getJsonText(pcJson);
		}

		PrintWriter out = resp.getWriter();
		out.println(jsonText);
	}

}