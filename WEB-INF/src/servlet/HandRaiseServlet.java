package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.HelpStatus;
import model.Pc;
import model.PcJson;
import network.NetworkInterface;
import network.ServletNetwork;
import servlet.helper.JsonHelper;

@WebServlet(urlPatterns = { "/v1/call/*" })
//call-teacher/XXXの応答関数
public class HandRaiseServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// クライアントIPアドレスの取得
		NetworkInterface network = new ServletNetwork(req);
		String clientId = network.getClientId();

		// クライアントPCを取得
		Pc pc = listManager.getPcFromId(clientId);

		if (pc != null) {
			pc.updateHelpStatus();

			// クライアントPCのリクエストタイムおハンドタイムを更新
			pc.updateLastRequestTime();
			pc.updateLastHandTime();

			// アクティブなPCを取得
			List<Pc> pcList = listManager.getActivePcList();

			// アクティブなPCの情報をJSON形式で出力
			PrintWriter out = resp.getWriter();
			String jsonText = JsonHelper.getJsonText(pcList);
			out.println(jsonText);

		} else {
			req.getRequestDispatcher("/error.html").forward(req,resp);
		}
	}


}