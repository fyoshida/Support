package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IpAddress;
import model.Student;
import model.StudentManager;
import network.INetwork;
import network.NetworkFactory;

@WebServlet(urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentManager studentManager = (StudentManager) sc.getAttribute("StudentManager");

		// クライアントIPアドレスの取得
		INetwork network = NetworkFactory.getNetwork(req);
		String ipAddress = network.getClientIpAddress();

		if (studentManager.existStudentByIpAddress(ipAddress)) {
			// ログイン名を取得
			String userName = req.getParameter("UserName");

			// 学生を取得
			Student student = studentManager.findStudentByIpAddress(ipAddress);

			// ログイン
			student.login(userName);

			//studentManagerを保存.
			sc.setAttribute("StudentManager", studentManager);
		}

		req.getRequestDispatcher("/index.html").forward(req, resp);
	}
}
