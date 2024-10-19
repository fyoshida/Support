package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.aggregate.StudentManager;
import domain.entities.Student;
import helper.JsonConverter;
import helper.PcJson;
import helper.PcJsonHelper;
import network.INetwork;
import network.NetworkFactory;

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
		StudentManager studentManager = (StudentManager) sc.getAttribute("StudentManager");

		// クライアントIPアドレスの取得
//		NetworkInterface network = new ServletNetwork(req);

		INetwork network = NetworkFactory.getNetwork(req);
		String ipAddressString = network.getClientIpAddress();
		InetAddress ipAddress = InetAddress.getByName(ipAddressString);

		// 学生を取得
		Optional<Student> optStudent = studentManager.getStudent(ipAddress);
		if (optStudent.isPresent()) {
			req.getRequestDispatcher("/error.html").forward(req, resp);
			return;
		}
		Student student=optStudent.get();
		
		// Student --> PcJson
		PcJson pcJson = PcJsonHelper.fromStudent(student);

		// JSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJson);
		out.println(jsonText);
	}

}