package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Pc;
import model.PcJson;
import network.NetworkInterface;
import network.ServletNetwork;
import servlet.helper.JsonHelper;

@WebServlet(urlPatterns = { "/v1/whoami" })
//whoamiの応答関数
public class GetClientPcServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// クライアントIPアドレスの取得
		NetworkInterface network = new ServletNetwork(req);
		String clientIpAddress = network.getClientIpAddress();

		// クライアントPCを取得
		Pc pc = listManager.getPcFromIpAddress(clientIpAddress);

		if (pc != null) {
			// クライアントPCのリクエストタイムを更新
			pc.updateLastRequestTime();

			// クライアントPCの情報をJSON形式で出力
			PrintWriter out = resp.getWriter();
			String jsonText = JsonHelper.getJsonText(pc);
			out.println(jsonText);

		} else {
			req.getRequestDispatcher("/error.html").forward(req, resp);
		}
	}

}