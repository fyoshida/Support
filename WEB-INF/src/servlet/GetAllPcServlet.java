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

import model.Pc;
import model.PcManager;
import servlet.helper.JsonHelper;
import servlet.helper.PcJson;
import servlet.helper.PcJsonConverter;

@WebServlet(urlPatterns = { "/v1/" })
//whoamiの応答関数
public class GetAllPcServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// PcManagerを取得
		ServletContext sc = getServletContext();
		PcManager pcManager=(PcManager)sc.getAttribute("PcManager");


		// Pc --> PcJson
		List<Pc> pcList = pcManager.getPcList();
		List<PcJson> pcJsonList=PcJsonConverter.getPcJsonFromPcList(pcList);

		// クライアントPCの情報をJSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonHelper.getJsonText(pcJsonList);
		out.println(jsonText);
	}

}