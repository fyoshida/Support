package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.services.StudentService;
import httpclient.HttpClientFactory;
import httpclient.IHttpClient;
import repository.IPcRepository;
import repository.RepositoryFactory;

@WebServlet(urlPatterns = { "/v1/initialize" })
//active-seatsの応答関数
public class InitializeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// リポジトリを取得
		IPcRepository repository=RepositoryFactory.getRepository();
		IHttpClient httpClient = HttpClientFactory.getNetwork(req);

		// StudentManagerを生成
		StudentService studentService=new StudentService(repository,httpClient);

		// StudentManagerをServletContextに保存
		ServletContext sc = getServletContext();
		sc.setAttribute("StudentService", studentService);

		// 開始用Servletへ移動
//		req.getRequestDispatcher("GetAllPcServlet").forward(req, resp);
	}

}