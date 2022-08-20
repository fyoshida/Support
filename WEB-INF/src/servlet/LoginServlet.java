package servlet;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pc;
import network.NetworkInterface;
import network.ServletNetwork;

@WebServlet(urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// クライアントIPアドレスの取得
		NetworkInterface network = new ServletNetwork(req);
		String clientIpAddress = network.getClientIpAddress();

		if(listManager.extisIpAddress(clientIpAddress)) {
			Pc pc = listManager.getPcByIpAddress();
			pc.login();
		}

//		if(pc != null) {
////			// ログイン成功時の処理
//			StartServiceServlet.setLogin(pc.getPcId(), true);
//			StartServiceServlet.setRequestTime(pc.getPcId());
//		}

		req.getRequestDispatcher("/index.html").forward(req,resp);
	}
}
