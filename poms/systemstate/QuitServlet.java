package hit.poms.systemstate;

import hit.poms.dao.entity.Employee;
import hit.poms.dao.entity.Substation;
import hit.poms.dsz.dao.SubDAO;
import hit.poms.login.dao.LoginDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuitServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int flag=0;
		String arg = req.getParameter("arg");
		if("quit".equals(arg)){
			flag=1;
		}else if("quittoo".equals(arg)){
			flag=2;
		}else if("in".equals(arg)){
			flag=0;
		}
		ServletContext context = this.getServletContext();
		context.setAttribute("dsz_flag", flag);
		System.out.println("----arg----"+arg+"----flag---"+flag);
		req.getRequestDispatcher("systemstate.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
