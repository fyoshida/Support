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

import model.IpAddress;
import model.Student;
import model.StudentManager;
import servlet.helper.JsonConverter;
import servlet.helper.NetworkHelper;
import servlet.helper.PcJson;
import servlet.helper.PcJsonConverter;

@WebServlet(urlPatterns = { "/v1/call/*" })
//call-teacher/XXXの応答関数
public class HandUpServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentManager studentManager=(StudentManager)sc.getAttribute("StudentManager");

		// クライアントIPアドレスの取得
		IpAddress ipAddress = NetworkHelper.getIpAddressWithServletNetwork(req);

		// 学生を取得
		Student student = studentManager.getStudent(ipAddress);
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
		List<PcJson> pcJsonList=PcJsonConverter.getPcJson(studentList);

		// JSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJsonList);
		out.println(jsonText);
	}


}