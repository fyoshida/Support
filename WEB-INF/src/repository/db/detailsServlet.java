package repository.db;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBSample.Item;
import DataBase.ItemManager;

@WebServlet(name="detailsServlet",urlPatterns={"/detailsServlet"})
public class detailsServlet extends HttpServlet{

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doMain(arg0,arg1);
	}

	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doMain(arg0,arg1);
	}

	protected void doMain(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
		ItemManager im = new ItemManager();
		String str=req.getParameter("ID");
		if(str!=null){
			int id = Integer.parseInt(str);
			Item item = (Item)im.get(id);
			session.setAttribute("Item",item);
			req.getRequestDispatcher("/detailsOutput.jsp").forward(req,res);
		}
	}
}
