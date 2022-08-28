package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pc;
import model.StudentManager;
import repository.RepositoryInterface;
import repository.dummy.DummyRepository;

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

		List<Pc> pcBeanList;
		try {
			// PcBeanの取得
			pcBeanList = repository.getPcList();

			// PcManagerを生成
			StudentManager pcManager=new StudentManager(pcBeanList);

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