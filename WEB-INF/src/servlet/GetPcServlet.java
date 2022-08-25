package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.IpAddress;
import model.IpAddressException;
import model.Pc;
import model.PcManager;
import network.NetworkInterface;
import network.ServletNetwork;
import servlet.helper.JsonHelper;
import servlet.helper.PcJson;
import servlet.helper.PcJsonConverter;

@WebServlet(urlPatterns = { "/v1/whoami" })
//whoamiの応答関数
public class GetPcServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// PcManagerを取得
		ServletContext sc = getServletContext();
		PcManager pcManager=(PcManager)sc.getAttribute("PcManager");

		// クライアントIPアドレスの取得
		NetworkInterface network = new ServletNetwork(req);
		String clientIpAddress = network.getClientIpAddress();
		IpAddress ipAddress=new IpAddress(clientIpAddress);


		// クライアントPCを取得
		Pc pc = pcManager.getPc(ipAddress);

		if (pc != null) {
			// Pc --> PcJson
			PcJson pcJson =PcJsonConverter.getPcJson(pc);

			// クライアントPCの情報をJSON形式で出力
			PrintWriter out = resp.getWriter();
			String jsonText = JsonHelper.getJsonText(pcJson);
			out.println(jsonText);

		} else {
			req.getRequestDispatcher("/error.html").forward(req, resp);
		}
	}

}