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
import servlet.helper.NetworkHelper;

@WebServlet(urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

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

		if(studentManager.existStudent(ipAddress)) {
			// 学生を取得
			Student student = studentManager.getStudent(ipAddress);

			// ログイン
			student.login("");

			//studentManagerを保存.
			sc.setAttribute("StudentManager", studentManager);
		}

//		if(pc != null) {
////			// ログイン成功時の処理
//			StartServiceServlet.setLogin(pc.getPcId(), true);
//			StartServiceServlet.setRequestTime(pc.getPcId());
//		}

		req.getRequestDispatcher("/index.html").forward(req,resp);
	}
}
