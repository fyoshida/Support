package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.aggregate.StudentManager;
import domain.entities.Student;
import helper.JsonConverter;
import helper.PcJson;
import helper.PcJsonHelper;

//@WebServlet(urlPatterns = { "/v1/" })
//whoamiの応答関数
public class GetAllPcServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentManager studentManager=(StudentManager)sc.getAttribute("StudentManager");

		// 全学生を取得
		List<Student> studentList = studentManager.getStudentList();

		// Student --> PcJson
		List<PcJson> pcJsonList=PcJsonHelper.getPcJson(studentList);

		// JSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJsonList);
		out.println(jsonText);
	}

}