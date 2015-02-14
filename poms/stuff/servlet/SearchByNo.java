package hit.poms.stuff.servlet;

import hit.poms.stuff.entity.Stuff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 此servlet是用于后台根据管理员输入的员工编号来查询其详细信息所用
 */
public class SearchByNo extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String no = req.getParameter("no");
		String arg = req.getParameter("arg");
		/*
		 * dao层代码根据编号来找该编号的详细信息，返回一个实体类
		 */
		Stuff stuff = new Stuff();
		req.setAttribute("stuff", stuff);
		if("delete".equals(arg)){
			req.getRequestDispatcher("delete_stuff.jsp").forward(req,resp);

		}
		else if("query".equals(arg)){
			req.getRequestDispatcher("query_stuff.jsp").forward(req,resp);

		}
		else if("update".equals(arg)){
			req.getRequestDispatcher("update_stuff.jsp").forward(req,resp);

			
		}
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
