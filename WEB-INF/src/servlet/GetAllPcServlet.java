package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.entities.Student;
import domain.valueobjects.IpAddress;
import helper.JsonConverter;
import helper.StudentJson;
import helper.StudentJsonHelper;
import services.StudentService;

//@WebServlet(urlPatterns = { "/v1/" })
//whoamiの応答関数
public class GetAllPcServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentService studentService=(StudentService)sc.getAttribute("StudentService");
		
		// 全学生を取得
		List<Student> studentList = studentService.getStudentList();

		// Student --> PcJson
		List<StudentJson> pcJsonList;
		if(session.getAttribute("Administrator")!=null) {
			pcJsonList=StudentJsonHelper.getPcJson(studentList);
		}else {
			Optional<Student> optStudent = studentService.getClientStudent();
			if( optStudent.isEmpty()) {
				return;
			}
			Student student = optStudent.get();
			IpAddress ipAddress=student.getPc().getIpAddress();
			pcJsonList=StudentJsonHelper.getPcJsonForStudent(studentList,ipAddress);
		}
		
		// JSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJsonList);
		out.println(jsonText);
	}

}