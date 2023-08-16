package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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

@jakarta.servlet.annotation.WebServlet(urlPatterns = { "/v1/whoami" })
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
		String ipAddress = network.getClientIpAddress();

		// 学生を取得
		Student student = studentManager.findStudentByIpAddress(ipAddress);
		if (student == null) {
			req.getRequestDispatcher("/error.html").forward(req, resp);
			return;
		}

		// Student --> PcJson
		PcJson pcJson = PcJsonHelper.getPcJson(student);

		// JSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJson);
		out.println(jsonText);
	}

}