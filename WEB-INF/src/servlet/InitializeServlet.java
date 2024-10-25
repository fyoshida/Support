package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/connect-websocket")
public class InitializeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		HttpSession session = req.getSession();

		String clientIp = req.getRemoteAddr();
		if (clientIp.equals("0:0:0:0:0:0:0:1")) {
			clientIp = "127.0.0.1";
		}

	    resp.getWriter().write(clientIp);

		session.setAttribute("clientIp", clientIp);

//		req.getRequestDispatcher("/index.html").forward(req, resp);
	}
}