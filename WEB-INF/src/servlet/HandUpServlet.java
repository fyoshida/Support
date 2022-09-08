package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonConverter;
import helper.PcJson;
import helper.PcJsonHelper;
import model.Student;
import model.StudentManager;
import network.INetwork;
import network.NetworkFactory;

@WebServlet(urlPatterns = { "/v1/call/*" })
//call-teacher/XXXの応答関数
public class HandUpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentManager studentManager=(StudentManager)sc.getAttribute("StudentManager");

		// クライアントのHostNameを取得
		INetwork network = NetworkFactory.getNetwork(req);
		String hostName = network.getClientHostName();

		// 学生を取得
		Student student = studentManager.findStudent(hostName);
		if (student == null) {
			req.getRequestDispatcher("/error.html").forward(req,resp);
			return;
		}

		// 手をあげる
		student.handUp();

		//pcManagerを保存.
		sc.setAttribute("StudentManager", studentManager);

		// 全学生を取得
		List<Student> studentList = studentManager.getStudentList();

		// Student --> PcJson
		List<PcJson> pcJsonList=PcJsonHelper.getPcJson(studentList);

		// JSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJsonList);
		out.println(jsonText);

//		// 全学生情報出力用Servletへ移動
//		req.getRequestDispatcher("GetAllPcServlet").forward(req, resp);
	}
}