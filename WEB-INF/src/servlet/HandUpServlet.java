package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.entities.Student;
import helper.JsonConverter;
import helper.StudentJson;
import helper.StudentJsonHelper;
import services.StudentService;

//@WebServlet(urlPatterns = { "/v1/call/*" })
//call-teacher/XXXの応答関数
public class HandUpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentService studentService=(StudentService)sc.getAttribute("StudentService");

		// 手をあげる
		studentService.clientHandUp();

		// 全学生を取得
		List<Student> studentList = studentService.getStudentList();

		// Student --> PcJson
		List<StudentJson> pcJsonList=StudentJsonHelper.getPcJson(studentList);

		// JSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJsonList);
		out.println(jsonText);

//		// 全学生情報出力用Servletへ移動
//		req.getRequestDispatcher("GetAllPcServlet").forward(req, resp);
	}
}