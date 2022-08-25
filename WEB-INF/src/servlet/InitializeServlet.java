package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Pc;
import model.PcManager;
import model.WaitingManager;
import repository.RepositoryInterface;
import repository.dummy.DummyRepository;
import repository.file.FileRepository;
import repository.list.ListRepository;
import servlet.helper.JsonHelper;

@WebServlet(urlPatterns = { "/v1/initialize" })
//active-seatsの応答関数
public class InitializeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 全PC情報を取得
//		RepositoryInterface repository = new FileRepository("/WEB-INF/data/pcIdTable.csv");
		RepositoryInterface repository = new DummyRepository();

		List<Pc> pcList;
		try {
			pcList = repository.getPcList();

			// PcManagerを生成
			PcManager pcManager=new PcManager(pcList);

			// 待ち行列マネージャーを設定
			WaitingManager waitingManager = new WaitingManager();
			for (Pc pc : pcList) {
				pc.setPriorityManager(waitingManager);
			}

			// PcListをServletContextに保存
			ServletContext sc = getServletContext();
			sc.setAttribute("PcManager", pcManager);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 開始用Servletへ移動
//		req.getRequestDispatcher("GetAllPcServlet").forward(req, resp);
	}

}