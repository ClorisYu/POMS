package hit.poms.login.servlet;

import hit.poms.login.dao.LoginDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4288947039456727159L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	       if(req.getParameter("type")!=null){
	    	   System.out.print("asdad");
	    	   String id=req.getParameter("id");
	    	   LoginDAO dao=new LoginDAO();
	    	   dao.logout(Long.parseLong(id));
	    	  // resp.sendRedirect("login.jsp");
	    	   req.getRequestDispatcher("login.jsp").forward(req, resp);
	       }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	

}
