package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.entities.Student;
import domain.services.StudentService;
import helper.JsonConverter;
import helper.PcJson;
import helper.PcJsonHelper;

@WebServlet(urlPatterns = { "/v1/whoami" })
//whoamiの応答関数
public class GetPcServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentService studentService=(StudentService)sc.getAttribute("StudentService");
		
		// クライアントIPアドレスの取得
//		NetworkInterface network = new ServletNetwork(req);

		Optional<Student> optStudent = studentService.getClientStudent();
		if( optStudent.isEmpty()) {
			return;
		}
		Student student = optStudent.get();
		
		// Student --> PcJson
		PcJson pcJson = PcJsonHelper.fromStudent(student);

		// JSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJson);
		out.println(jsonText);
	}

}